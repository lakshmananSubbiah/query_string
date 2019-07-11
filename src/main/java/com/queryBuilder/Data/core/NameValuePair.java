package com.queryBuilder.Data.core;

/**
 * 
 * @author Lakshmanan Subbiah
 * 09-07-2019 
 * This class holds the Name and Score of individual data.
 * This class will be used in the comparision.
 */
public class NameValuePair {

	private String name;
	
	private int score;
	
	public NameValuePair(String name, int score) {
		if(name==null) {
			throw new RuntimeException("Name cannot be null.");
		}
		this.name = name;
		this.score = score;
		
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}
	
	public String[] getAllSubStrings() {
		return this.name.split("_");
	}
	
	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!NameValuePair.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final NameValuePair other = (NameValuePair) obj;
        if ((this.getName() == null) ? (other.getName() != null) : !this.getName().equals(other.getName())) {
            return false;
        }

        if (this.getScore() != other.getScore()) {
            return false;
        }

        return true;
    }
	
	@Override
	public int hashCode() {
		return this.getName().hashCode()+score;
	}
	
	@Override
	public String toString() {
		return "Name: "+this.getName()+" Score: "+this.getScore();
	}
	
}
