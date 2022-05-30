package individualprojectpartb.database;

import static individualprojectpartb.database.CourseTrainerDao.FINDALL;
import static individualprojectpartb.database.CourseTrainerDao.FINDBYID;
import individualprojectpartb.entities.Trainer;
import individualprojectpartb.entities.Course;
import individualprojectpartb.entities.CourseTrainer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseTrainerDao extends GenericDetails implements GeneralCommands<CourseTrainer>{
    
    public static final String FINDALL = "SELECT * FROM COURSE_TRAINER";
    public static final String FINDBYID = "SELECT * FROM COURSE_TRAINER WHERE COTR_ID=?";
    private static final String INSERT = "INSERT INTO COURSE_TRAINER (course_ID,trainer_ID) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE COURSE_TRAINER SET course_ID=?,trainer_ID=? WHERE COTR_ID = ?";
    private static final String DELETE = "DELETE FROM COURSE_TRAINER WHERE COTR_ID = ?";

    //creating the list the connects trainers and courses
    @Override
    public List<CourseTrainer> findAll() {
        List<CourseTrainer> list = new ArrayList();
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement(FINDALL);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int CoAsId = rs.getInt("COTR_ID");
                int courseId = rs.getInt("course_ID");
                Course course = getCourseById(courseId);
                int trainerId = rs.getInt("trainer_ID");
                Trainer trainer = getTrainerById(trainerId);
                CourseTrainer CT = new CourseTrainer(CoAsId, course, trainer);
                list.add(CT);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CourseTrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(rs, pstm, conn);
        }
        return list;
    }

    //finding a registration of a trainer attached to a course by the id
    @Override
    public CourseTrainer findById(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        CourseTrainer CT = null;
        CourseDao courseDao = new CourseDao();
        TrainerDao trainerDao = new TrainerDao();
        try {
            pstm = conn.prepareStatement(FINDBYID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            rs.next();
            int CoAsId = rs.getInt("COTR_ID");
            int courseId = rs.getInt("course_ID");
            Course course = getCourseById(courseId);
            int trainerId = rs.getInt("trainer_ID");
            Trainer trainer = getTrainerById(trainerId);
            CT = new CourseTrainer(CoAsId, course, trainer);
        } catch (SQLException ex) {
            String message = "There is no course and trainer together with id " + id;
            Logger.getLogger(CourseTrainerDao.class.getName()).log(Level.SEVERE, message);
        } finally {
            closeConnections(rs, pstm, conn);
        }
        return CT;
    }

    //creating a registration of a trainer teaching a course
    @Override
    public void create(CourseTrainer CT) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(INSERT);
            pstm.setInt(1,CT.getCourse().getCoId());
            pstm.setInt(2,CT.getTrainer().getTrId());
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Course has an trainer!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseTrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }

    //updating a registration of a trainer teaching a course
    @Override
    public void update(CourseTrainer CT) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(UPDATE);
            pstm.setInt(1,CT.getCourse().getCoId());
            pstm.setInt(2,CT.getTrainer().getTrId());
            pstm.setInt(3, CT.getCOTRID());
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Course combined with trainer successfully updated!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseTrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }

    //deleting a registration of a trainer teaching a course
    @Override
    public void delete(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(DELETE);
            pstm.setInt(1, id);
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Course combined with trainer successfully deleted!!");
            }
        } catch (SQLException ex) {
            System.err.println("Course with this trainer not found");
            Logger.getLogger(CourseTrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }
    
    //method to find a specif course by the id
    private Course getCourseById(int id) {
        CourseDao courseDao = new CourseDao();
        Course course = courseDao.findById(id);
        return course;
    }

    //method to find a specific trainer by the id
    private Trainer getTrainerById(int id) {
        TrainerDao trainerDao = new TrainerDao();
        Trainer trainer = trainerDao.findById(id);
        return trainer;
    }
    
}
