package org.slc.sli.api.security.context.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slc.sli.api.constants.EntityNames;
import org.slc.sli.api.resources.SecurityContextInjector;
import org.slc.sli.api.security.context.PagingRepositoryDelegate;
import org.slc.sli.api.security.roles.SecureRoleRightAccessImpl;
import org.slc.sli.api.test.WebContextTestExecutionListener;
import org.slc.sli.domain.Entity;
import org.slc.sli.domain.NeutralQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

/**
 * Unit tests for staff --> gradingPeriod entity context validator.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/applicationContext-test.xml" })
@TestExecutionListeners({ WebContextTestExecutionListener.class, DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class })
public class StaffToGradingPeriodValidatorTest {
    private static final String STAFF_ID = "1";

    @Autowired
    private StaffToGradingPeriodValidator validator;

    @Autowired
    private SecurityContextInjector injector;

    @Autowired
    private PagingRepositoryDelegate<Entity> repo;

    @Autowired
    private ValidatorTestHelper helper;

    @Before
    public void setUp() {
        String user = "fake staff";
        String fullName = "Fake Staff";
        List<String> roles = Arrays.asList(SecureRoleRightAccessImpl.IT_ADMINISTRATOR);

        Entity entity = Mockito.mock(Entity.class);
        Mockito.when(entity.getType()).thenReturn("staff");
        Mockito.when(entity.getEntityId()).thenReturn(STAFF_ID);
        injector.setCustomContext(user, fullName, "DERPREALM", roles, entity, "123");
    }
    
    @After
    public void tearDown() {
        repo.deleteAll(EntityNames.GRADING_PERIOD, new NeutralQuery());
        repo.deleteAll(EntityNames.SESSION, new NeutralQuery());
        repo.deleteAll(EntityNames.EDUCATION_ORGANIZATION, new NeutralQuery());
        SecurityContextHolder.clearContext();
    }

    @Test
    public void testCanValidation() throws Exception {
        assertTrue(validator.canValidate(EntityNames.GRADING_PERIOD, true));
        assertTrue(validator.canValidate(EntityNames.GRADING_PERIOD, false));

        assertFalse(validator.canValidate(EntityNames.ASSESSMENT, true));
        assertFalse(validator.canValidate(EntityNames.ATTENDANCE, true));
        assertFalse(validator.canValidate(EntityNames.COHORT, false));
        assertFalse(validator.canValidate(EntityNames.COURSE, true));
        assertFalse(validator.canValidate(EntityNames.DISCIPLINE_ACTION, false));
        assertFalse(validator.canValidate(EntityNames.DISCIPLINE_INCIDENT, true));
        assertFalse(validator.canValidate(EntityNames.EDUCATION_ORGANIZATION, false));
        assertFalse(validator.canValidate(EntityNames.GRADE, true));
        assertFalse(validator.canValidate(EntityNames.GRADEBOOK_ENTRY, false));
        assertFalse(validator.canValidate(EntityNames.SESSION, false));
        assertFalse(validator.canValidate(EntityNames.LEARNING_OBJECTIVE, true));
        assertFalse(validator.canValidate(EntityNames.LEARNING_STANDARD, false));
        assertFalse(validator.canValidate(EntityNames.PARENT, false));
        assertFalse(validator.canValidate(EntityNames.PROGRAM, true));
        assertFalse(validator.canValidate(EntityNames.SCHOOL, true));
        assertFalse(validator.canValidate(EntityNames.STUDENT, false));
        assertFalse(validator.canValidate(EntityNames.STAFF, true));
        assertFalse(validator.canValidate(EntityNames.SECTION, false));
        assertFalse(validator.canValidate(EntityNames.REPORT_CARD, false));
        assertFalse(validator.canValidate(EntityNames.TEACHER, true));
        assertFalse(validator.canValidate(EntityNames.STAFF_COHORT_ASSOCIATION, false));
        assertFalse(validator.canValidate(EntityNames.STAFF_PROGRAM_ASSOCIATION, true));
        assertFalse(validator.canValidate(EntityNames.TEACHER_SCHOOL_ASSOCIATION, false));
        assertFalse(validator.canValidate(EntityNames.STUDENT_ASSESSMENT, true));
        assertFalse(validator.canValidate(EntityNames.STUDENT_PARENT_ASSOCIATION, false));
    }

    @Test
    public void testSingleGradingPeriodGoodValidation() {
        // Data setup
        String seaId = helper.generateEdorgWithParent(null).getEntityId();
        String leaId = helper.generateEdorgWithParent(seaId).getEntityId();
        String schoolId = helper.generateEdorgWithParent(leaId).getEntityId();
        helper.generateStaffEdorg(STAFF_ID, schoolId, false);
        String gradingPeriodId = helper.generateGradingPeriod().getEntityId();
        helper.generateSession(schoolId, Arrays.asList(gradingPeriodId)).getEntityId();
        
        // Test Validate
        Set<String> gradingPeriods = new HashSet<String>();
        gradingPeriods.add(gradingPeriodId);
        assertTrue(validator.validate(EntityNames.GRADING_PERIOD, gradingPeriods));
    }

    @Test
    public void testSameSessionGradingPeriodsValidation() {
        // Data setup
        String seaId = helper.generateEdorgWithParent(null).getEntityId();
        String leaId = helper.generateEdorgWithParent(seaId).getEntityId();
        String schoolId = helper.generateEdorgWithParent(leaId).getEntityId();
        helper.generateStaffEdorg(STAFF_ID, schoolId, false);
        String aGradingPeriodId = helper.generateGradingPeriod().getEntityId();
        String bGradingPeriodId = helper.generateGradingPeriod().getEntityId();
        helper.generateSession(schoolId, Arrays.asList(aGradingPeriodId, bGradingPeriodId)).getEntityId();
        
        // Test Validate
        Set<String> gradingPeriods = new HashSet<String>();
        gradingPeriods.add(aGradingPeriodId);
        gradingPeriods.add(bGradingPeriodId);
        assertTrue(validator.validate(EntityNames.GRADING_PERIOD, gradingPeriods));
    }

    @Test
    public void testDifferentSessionGradingPeriodsValidation() {
        // Data setup
        String seaId = helper.generateEdorgWithParent(null).getEntityId();
        String leaId = helper.generateEdorgWithParent(seaId).getEntityId();
        String schoolId = helper.generateEdorgWithParent(leaId).getEntityId();
        helper.generateStaffEdorg(STAFF_ID, schoolId, false);
        String aGradingPeriodId = helper.generateGradingPeriod().getEntityId();
        String bGradingPeriodId = helper.generateGradingPeriod().getEntityId();
        helper.generateSession(schoolId, Arrays.asList(aGradingPeriodId)).getEntityId();
        helper.generateSession(leaId, Arrays.asList(bGradingPeriodId)).getEntityId();
        
        // Test Validate
        Set<String> gradingPeriods = new HashSet<String>();
        gradingPeriods.add(aGradingPeriodId);
        gradingPeriods.add(bGradingPeriodId);
        assertTrue(validator.validate(EntityNames.GRADING_PERIOD, gradingPeriods));
    }
    
    @Test
    public void testExpiredStaffSchoolValidation() {
        // Data setup
        String seaId = helper.generateEdorgWithParent(null).getEntityId();
        String leaId = helper.generateEdorgWithParent(seaId).getEntityId();
        String schoolId = helper.generateEdorgWithParent(leaId).getEntityId();
        helper.generateStaffEdorg(STAFF_ID, schoolId, true);
        String gradingPeriodId = helper.generateGradingPeriod().getEntityId();
        helper.generateSession(schoolId, Arrays.asList(gradingPeriodId)).getEntityId();
        
        // Test Validate
        Set<String> gradingPeriods = new HashSet<String>();
        gradingPeriods.add(gradingPeriodId);
        assertFalse(validator.validate(EntityNames.GRADING_PERIOD, gradingPeriods));
    }
    
    @Test
    public void testSeperateStaffSchoolValidation() {
        // Data setup
        String seaId = helper.generateEdorgWithParent(null).getEntityId();
        String leaId = helper.generateEdorgWithParent(seaId).getEntityId();
        String aSchoolId = helper.generateEdorgWithParent(leaId).getEntityId();
        String bSchoolId = helper.generateEdorgWithParent(leaId).getEntityId();
        helper.generateStaffEdorg(STAFF_ID, aSchoolId, false);
        String gradingPeriodId = helper.generateGradingPeriod().getEntityId();
        helper.generateSession(bSchoolId, Arrays.asList(gradingPeriodId)).getEntityId();
        
        // Test Validate
        Set<String> gradingPeriods = new HashSet<String>();
        gradingPeriods.add(gradingPeriodId);
        assertFalse(validator.validate(EntityNames.GRADING_PERIOD, gradingPeriods));
    }
    
    @Test
    public void testMultipleGradingPeriodMixedValidation() {
        // Data setup
        String seaId = helper.generateEdorgWithParent(null).getEntityId();
        String leaId = helper.generateEdorgWithParent(seaId).getEntityId();
        String aSchoolId = helper.generateEdorgWithParent(leaId).getEntityId();
        String bSchoolId = helper.generateEdorgWithParent(leaId).getEntityId();
        helper.generateStaffEdorg(STAFF_ID, aSchoolId, false);
        String aGradingPeriodId = helper.generateGradingPeriod().getEntityId();
        String bGradingPeriodId = helper.generateGradingPeriod().getEntityId();
        helper.generateSession(aSchoolId, Arrays.asList(aGradingPeriodId)).getEntityId();
        helper.generateSession(bSchoolId, Arrays.asList(bGradingPeriodId)).getEntityId();
        
        // Test Validate
        Set<String> gradingPeriods = new HashSet<String>();
        gradingPeriods.add(aGradingPeriodId);
        gradingPeriods.add(bGradingPeriodId);
        assertFalse(validator.validate(EntityNames.GRADING_PERIOD, gradingPeriods));
    }
}
