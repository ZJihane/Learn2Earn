package DAO_Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Beans.student;
import DAO.DAOException;
import DAO.DAOFactory;

public class studentDaoImpl implements studentDAO {

	private DAOFactory daoFactory;

    public studentDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
    
  

    @Override
    public student create(student student) throws DAOException {
        final String SQL_INSERT = "INSERT INTO student (first_name, last_name, email, phone, linkedin, cv, picture, skill) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            // Specify the column names to be returned
            preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            // Set the parameters
            preparedStatement.setString(1, student.getFirst_name());
            preparedStatement.setString(2, student.getLast_name());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setString(4, student.getPhone());
            preparedStatement.setString(5, student.getLinkedin());
            preparedStatement.setBytes(6, student.getCv());
            preparedStatement.setBytes(7, student.getPicture());
            preparedStatement.setString(8, student.getSkill());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    // Set the generated ID to the student object
                    student.setId_student(generatedKeys.getInt(1));

                    // Return the created student object
                    return student;
                }
            }

            throw new DAOException("Creating student failed, no ID obtained.");
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            // ClosingAll(preparedStatement, connection);
        }
    }





		
	
	private static student map( ResultSet resultSet ) throws SQLException {
		 student student = new student();
		student.setId_student(resultSet.getInt("id_student"));
		student.setFirst_name( resultSet.getString( "first_name" ) );
		student.setLast_name( resultSet.getString( "last_name" ) );
		student.setEmail( resultSet.getString( "email" ) );
		student.setPhone( resultSet.getString( "phone" ) );
		student.setLinkedin( resultSet.getString( "linkedin" ) );
		student.setCv( resultSet.getBytes( "cv" ) );
		student.setPicture( resultSet.getBytes("picture") );
		student.setSkill( resultSet.getString( "skill" ) );
		return student;
		}
    
    
	public static PreparedStatement initRequestPrepare(Connection connexion, String sql, Object... objets) throws SQLException {
	    PreparedStatement preparedStatement = connexion.prepareStatement(sql);
	    for (int i = 0; i < objets.length; i++) {
	        preparedStatement.setObject(i + 1, objets[i]);
	    }
	    return preparedStatement;
	}

    

	@Override
	public student find(int id_student) throws DAOException {
	    final String SQL_SELECT_PAR_ID = "SELECT  id_student ,first_name, last_name, email, phone, linkedin, cv, picture , skill FROM student WHERE id_student=?";
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    student student = null;

	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare(connexion, SQL_SELECT_PAR_ID, id_student);
	        resultSet = preparedStatement.executeQuery();
	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
	        if (resultSet.next()) {
	            student = map(resultSet);
	        }
	    } catch (SQLException e) {
	        throw new DAOException(e);
	    } finally {
	        // ClosingAll(resultSet, preparedStatement, connexion);
	    }

	    return student;
	}

	
	@Override 
	public List<student> getAllUsers()  {
		
		final String SQL_SELECT_ALL = "SELECT id_student ,first_name, last_name, email,phone, linkedin,cv, picture , skill FROM student";
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    student student = null;
	    List<student> allstudent = new ArrayList<student>();
	    
	    try {
	        /* Récupération d'une connexion depuis la Factory */
	        connexion = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare( connexion, SQL_SELECT_ALL);
	        resultSet = preparedStatement.executeQuery();
	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
	        while ( resultSet.next() ) {
	            student = map( resultSet );
	            allstudent.add(student);
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        //ClosingAll( resultSet, preparedStatement, connexion );
	    }

		
		return allstudent ;
	}
	
	
	
	
	

	@Override
	public void update(student student) throws DAOException {
	    final String SQL_UPDATE = "UPDATE student SET first_name=?, last_name=?, email=?, phone=?, linkedin=?, cv=?, picture=? ,skill=? WHERE id_student=?";
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        connection = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare(connection, SQL_UPDATE,
	            student.getFirst_name(),
	            student.getLast_name(),
	            student.getEmail(),
	            student.getPhone(),
	            student.getLinkedin(),
	            student.getCv(),
	            student.getPicture(),
	            student.getSkill(),
	            student.getId_student() // Assuming getIdStudent() returns the student's unique identifier
	        );
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        throw new DAOException(e);
	    } finally {
	        // ClosingAll(preparedStatement, connection);
	    }
	}


	@Override
	public void delete(int idStudent) throws DAOException {
	    final String SQL_DELETE = "DELETE FROM student WHERE id_student=?";
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        connection = daoFactory.getConnection();
	        preparedStatement = initRequestPrepare(connection, SQL_DELETE, idStudent);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        throw new DAOException(e);
	    } finally {
	        // ClosingAll(preparedStatement, connection);
	    }
	}
	

}