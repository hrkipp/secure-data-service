import pymongo
import common 

MAX_ARG_LENGTH = 1024 

def process_stats(start_time, end_time, stats):
    stack = [(None, None, [])]
    for oneStat in stats:
        stype, ssub_type, sid, sval, args = (oneStat + [None])[:5]
        if stype == "e": 
            if ssub_type == "start":
                stack.append((sid, sval, []))
            elif ssub_type == "end":
                start_sid, start_val, sub_requests = stack.pop()
                duration = sval - start_val 
                if duration > 0:
                    stack[-1][2].append((start_sid, start_val-start_time, duration, args, sub_requests))
            else:
                raise KeyError("Unknown subtype:" + ssub_type)
    d, d, result = stack.pop() 
    return result 

def get_testruns(col):
    result = {}
    for testrun in col.distinct("body.reqid"):
        req_id_convention = testrun.split(":", 1)
        if len(req_id_convention) == 2: 
            testname, instance = req_id_convention
            if instance.startswith("t-"):
                result.setdefault(testname, []).append(testrun)
    return result

def print_stats(stats, call_threshold_ms, indent="", uniq_types={}):
    for oneStat in stats: 
        stat_id, stat_delta, stat_duration, stat_args, stat_subrequests = oneStat
        # if stat_args:
        #     for arg in stat_args:
        #         if arg:
        #             arg_type, arg_val = arg.split(":",1)
        #             uniq_types.setdefault(arg_type, []).append(len(str(arg_val)))

        # args_string = str(stat_args).lstrip("[").strip("]")
        # if len(args_string) > 200: 
        #     import pdb; pdb.set_trace() 
        #     args_string = "Number of arguments:" + str(len(stat_args))
        try: 
            print "%s%s:%s: %s" % (indent + "    ", stat_delta, stat_duration, stat_id)
        except:
            import pdb; pdb.set_trace() 
        if stat_duration > call_threshold_ms: 
            print indent + "    " + "  ---------"
            print indent + "    " + "  | Args: |"
            print indent + "    " + "  ---------"
            for oneArg in stat_args: 
                arg_type, arg_val = oneArg.split(":", 1)
                arg_type = arg_type.rsplit(".", 1)[1]
                arg_str = arg_type + ":" + arg_val
                if len(arg_str) > MAX_ARG_LENGTH: 
                    arg_str = arg_str[:MAX_ARG_LENGTH] + "..." + ("[length = %s]" % len(arg_str))
                print indent + "    " + "  " + arg_str
        print_stats(stat_subrequests, call_threshold_ms, indent + "    ", uniq_types)
    # if indent == "":
    #     print "Unique types:"
    #     for k,v in uniq_types.iteritems():
    #         print k, ":", sorted(v) 

def extract_by_testrun(testrun, col, req_threshold_ms):
    by_response_time = []
    slow_requests = [] 

    q_result = col.find({"body.reqid" : testrun})
    for req in q_result:
        b = req['body']
        url = b['url']
        start = b['startTime']
        end = b['endTime']
        duration = end - start 
        req_id = b.get('reqid', "NO REQUEST ID")
        by_response_time.append((duration, url))
        if duration > req_threshold_ms:
            stats = process_stats(start, end, b['stats'])
            slow_requests.append((duration, url, stats))

    # return the slow requests according to the threshold
    return (by_response_time, slow_requests)

def print_slow_requests(slow_requests, call_threshold_ms): 
    for entry in slow_requests:
        duration, url, stats = entry
        print "%s: %s" % (duration, url)
        print_stats(stats, call_threshold_ms)
        print "-" * 60 

if __name__=="__main__":
    q = {}
    con = pymongo.Connection()
    db = con[common.PERF_DB]
    col = db[common.PERF_COLLECTION]

    req_threshold_ms = 100
    call_threshold_ms = 10

    testruns = get_testruns(col)
    print "Found the following testruns:"
    for k in sorted(testruns.keys()): 
        print "    " + k
        for instance in testruns[k]:
            print "        ", instance 

    for testname, instances in testruns.iteritems():
        for oneTestRun in instances:
            print ("-" * 60)
            all_requests, slow_requests = extract_by_testrun(oneTestRun, col, req_threshold_ms)
            slow_requests.sort() 
            print_slow_requests(slow_requests, call_threshold_ms)



