package l05;

import java.io.Serializable;

/**
 *
 * @author wang
 */
public class Student implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the grade
     */
    public Double getGrade() {
        return grade;
    }

    /**
     * @param grade the grade to set
     */
    public void setGrade(double grade) {
        this.grade = grade;
    }
    private String name;
    private Double grade;
    
    public Student(String s,Double d){
        this.grade = d;
        this.name = s;
    }
}