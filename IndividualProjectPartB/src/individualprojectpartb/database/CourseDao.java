package individualprojectpartb.database;

import static individualprojectpartb.database.AssignmentDao.FINDALL;
import static individualprojectpartb.database.StudentDao.FINDALL;
import static individualprojectpartb.database.TrainerDao.FINDALL;
import individualprojectpartb.entities.Assignment;
import individualprojectpartb.entities.Course;
import individualprojectpartb.entities.Student;
import individualprojectpartb.entities.Trainer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseDao extends GenericDetails implements GeneralCommands<Course> {

    public static final String FINDALL = "SELECT * FROM COURSE";
    public static final String FINDBYID = "SELECT * FROM COURSE WHERE COID=?";
    private static final String INSERT = "INSERT INTO COURSE (title,stream,type,start_date,end_date) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE COURSE SET title=?,stream=?,type=?,start_date=?,end_date=? WHERE COID = ?";
    private static final String DELETE = "DELETE FROM COURSE WHERE COID = ?";
    private static final String FINDSTUDENTSPERONECOURSE="select student.stid, student.f_name, student.l_name, student.tuition_fees,student.date_of_birth"
     + " from student_course inner join course on student_course.course_id=course.COID AND course.COID=? inner join student on student_course.student_id=student.STID;";
    private static final String FINDTRAINERSPERONECOURSE="select trainer.TRID,trainer.f_name,trainer.l_name,trainer.subject from course_trainer inner "
    + "join course on course_trainer.course_id=course.COID AND course.COID=? inner join trainer on course_trainer.trainer_id=trainer.TRID";
    private static final String FINDASSIGNMENTSPERONECOURSE="select assignment.ASID,assignment.type,assignment.description,assignment.sub_date,"
            + "assignment.oral_mark,assignment.total_mark from course_assign inner join course on course_assign.course_id=course.COID AND course.COID=? "
            + "inner join assignment on course_assign.assignment_id=assignment.ASID";
    
    
    //creating the list of all courses
    @Override
    public List<Course> findAll() {
        List<Course> list = new ArrayList();
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement(FINDALL);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int CoId = rs.getInt("COID");
                String title = rs.getString("title");
                String stream = rs.getString("stream");
                String type = rs.getString("type");
                Date date1 = rs.getDate("start_date");
                LocalDate startDate = date1.toLocalDate();
                Date date2 = rs.getDate("end_date");
                LocalDate endDate = date2.toLocalDate();
                Course course = new Course(CoId, title, stream, type, startDate, endDate);
                list.add(course);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
        closeConnections(rs,pstm,conn);
    }
        return list;
    }

    //finding a specific course by the id
    @Override
    public Course findById(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Course course = null;
        try {
            pstm = conn.prepareStatement(FINDBYID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            rs.next();
            int CoId = rs.getInt("COID");
            String title = rs.getString("title");
            String stream = rs.getString("stream");
            String type = rs.getString("type");
            Date date1 = rs.getDate("start_date");
            LocalDate startDate = date1.toLocalDate();
            Date date2 = rs.getDate("end_date");
            LocalDate endDate = date2.toLocalDate();
            course = new Course(CoId, title, stream, type, startDate, endDate);
        } catch (SQLException ex) {
            String message = "Course with id " + id + " could not be found!!!";
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, message);
        } finally {
            closeConnections(rs, pstm, conn);
        }
        return course;
    }

    //creating a course
    @Override
    public void create(Course course) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(INSERT);
            pstm.setString(1, course.getTitle());
            pstm.setString(2, course.getStream());
            pstm.setString(3, course.getType());
            Date dateStart = Date.valueOf(course.getStartDate());
            pstm.setDate(4, dateStart);
            Date dateEnd = Date.valueOf(course.getEndDate());
            pstm.setDate(5, dateEnd);
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Course successfully created!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }

    //updating an existing course
    @Override
    public void update(Course course) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(UPDATE);
            pstm.setString(1, course.getTitle());
            pstm.setString(2, course.getStream());
            pstm.setString(3, course.getType());
            Date dateStart = Date.valueOf(course.getStartDate());
            pstm.setDate(4, dateStart);
            Date dateEnd = Date.valueOf(course.getEndDate());
            pstm.setDate(5, dateEnd);
            pstm.setInt(6, course.getCoId());
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Course successfully updated!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }

    //deleting an existing course
    @Override
    public void delete(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(DELETE);
            pstm.setInt(1, id);
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Course successfully deleted!!");
            }
        } catch (SQLException ex) {
            System.err.println("Course not found");
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }
    
    //creating the list of courses one student attends
    public List<Student> findStudentsPerCourse(int id) {
        List<Student> list =new ArrayList();
            Connection conn=getConnection();
            PreparedStatement pstm=null;
            ResultSet rs = null;
        try {
            pstm=conn.prepareStatement(FINDSTUDENTSPERONECOURSE);
            pstm.setInt(1, id);
            rs=pstm.executeQuery();
            while (rs.next()){
                int StId=rs.getInt("STID");
                String firstName=rs.getString("f_name");
                String lastName=rs.getString("l_name");
                int tuitionFees=rs.getInt("tuition_fees");
                Date dateOfBirth=rs.getDate("date_of_birth");
                LocalDate date=dateOfBirth.toLocalDate();
                Student student=new Student(StId,firstName,lastName,tuitionFees,date);
                list.add(student);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
        closeConnections(rs,pstm,conn);
    }
        return list;
    }
    
    //creating the list of traines that teach one course
    public List<Trainer> findTrainersPerCourse(int id) {
        List<Trainer> list = new ArrayList();
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement(FINDTRAINERSPERONECOURSE);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int TrId = rs.getInt("TRID");
                String firstName = rs.getString("f_name");
                String lastName = rs.getString("l_name");
                String subject = rs.getString("subject");
                Trainer trainer = new Trainer(TrId, firstName, lastName, subject);
                list.add(trainer);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
        closeConnections(rs,pstm,conn);
    }
        return list;
    }
    
    //creating the list of assignments one course has
    public List<Assignment> findAssignmentsPerCourse(int id) {
        List<Assignment> list = new ArrayList();
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement(FINDASSIGNMENTSPERONECOURSE);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int AsId = rs.getInt("ASID");
                String type = rs.getString("type");
                String description = rs.getString("description");
                int oralMark = rs.getInt("oral_mark");
                int totalMark = rs.getInt("total_mark");
                Date subDate = rs.getDate("sub_date");
                LocalDate date = subDate.toLocalDate();
                Assignment assignment = new Assignment(AsId, type, description, date, oralMark, totalMark);
                list.add(assignment);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
        closeConnections(rs,pstm,conn);
    }
        return list;
    }

}
