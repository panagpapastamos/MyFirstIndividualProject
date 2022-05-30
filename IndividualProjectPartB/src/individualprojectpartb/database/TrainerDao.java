package individualprojectpartb.database;

import individualprojectpartb.entities.Trainer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrainerDao extends GenericDetails implements GeneralCommands<Trainer> {

    public static final String FINDALL = "SELECT * FROM TRAINER";
    public static final String FINDBYID = "SELECT * FROM TRAINER WHERE TRID=?";
    private static final String INSERT = "INSERT INTO TRAINER (f_name,l_name,subject) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE TRAINER SET f_name=? ,l_name=? ,subject=? WHERE trid = ?";
    private static final String DELETE = "DELETE FROM TRAINER WHERE TRID = ?";

    //method that creates a list of all the trainers
    @Override
    public List<Trainer> findAll() {
        List<Trainer> list = new ArrayList();
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement(FINDALL);
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

    //method that finds a trainer by his id
    @Override
    public Trainer findById(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Trainer trainer = null;
        try {
            pstm = conn.prepareStatement(FINDBYID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            rs.next();
            int TrId = rs.getInt("TRID");
            String firstName = rs.getString("f_name");
            String lastName = rs.getString("l_name");
            String subject = rs.getString("subject");
            trainer = new Trainer(TrId, firstName, lastName, subject);
        } catch (SQLException ex) {
            String message = "Trainer with id " + id + " could not be found!!!";
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, message);
        } finally {
            closeConnections(rs, pstm, conn);
        }
        return trainer;
    }

    //method that creates a trainer
    @Override
    public void create(Trainer trainer) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(INSERT);
            pstm.setString(1, trainer.getFirstName());
            pstm.setString(2, trainer.getLastName());
            pstm.setString(3, trainer.getSubject());
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Trainer successfully created!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }

    //methodt hat that updates the fields of an existing trainer
    @Override
    public void update(Trainer trainer) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(UPDATE);
            pstm.setString(1, trainer.getFirstName());
            pstm.setString(2, trainer.getLastName());
            pstm.setString(3, trainer.getSubject());
            pstm.setInt(4, trainer.getTrId());
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Trainer successfully updated!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }

    //method that delets a trainer by his id
    @Override
    public void delete(int id) {
        Connection conn = getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(DELETE);
            pstm.setInt(1, id);
            int result = pstm.executeUpdate();
            if (result == 1) {
                System.out.println("Trainer successfully deleted!!");
            }
        } catch (SQLException ex) {
            System.err.println("Trainer not found");
            Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnections(pstm, conn);
        }
    }

}
