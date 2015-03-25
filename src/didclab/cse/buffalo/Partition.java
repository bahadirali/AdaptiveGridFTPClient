package didclab.cse.buffalo;

import didclab.cse.buffalo.hysterisis.Entry;
import stork.util.XferList;

/**
 * Represents a calculated partition. A partition contains the centroid and
 * the data points.
 */
public class Partition {
    /* centroid of the partition */
    private double centroid;
    
    /*variables for log based approach*/
    //Observed max values for each parameters in the log files
    int maxCC = Integer.MIN_VALUE, maxP = Integer.MIN_VALUE, maxPPQ = Integer.MIN_VALUE;
    
    public Entry entry;
    
    /* records belonging to this partition */
    private XferList fileList = new XferList("", "") ;

    /**
	 * @return the maxCC
	 */
	public int getMaxCC() {
		return maxCC;
	}
	/**
	 * @param maxCC the maxCC to set
	 */
	public void setMaxCC(int maxCC) {
		this.maxCC = maxCC;
	}
	/**
	 * @return the maxP
	 */
	public int getMaxP() {
		return maxP;
	}
	/**
	 * @param maxP the maxP to set
	 */
	public void setMaxP(int maxP) {
		this.maxP = maxP;
	}
	/**
	 * @return the maxPPQ
	 */
	public int getMaxPPQ() {
		return maxPPQ;
	}
	/**
	 * @param maxPPQ the maxPPQ to set
	 */
	public void setMaxPPQ(int maxPPQ) {
		this.maxPPQ = maxPPQ;
	}
	/**
	 * @return the density
	 */
	
	public Partition(double centroid) {
        this.centroid = centroid;
    }
    public Partition() {
        this.centroid = 0;
    	entry = new Entry();
    }
    
    public void setEntry(Entry e){
    	entry.setBandwidth(e.getBandwidth());
    	entry.setRtt(e.getRtt());
    	entry.setBDP(e.getBDP());
    	entry.setBufferSize(e.getBufferSize());
    	entry.setDedicated(e.isDedicated());
    }
    public double getCentroid() {
        return centroid;
    }

    public XferList getRecords() {
        return fileList;
    }

    public void addRecord(XferList.Entry e) {
        fileList.add(e.path,e.size);
    }

    /**
     * Calculate the new centroid from the data points. Mean of the
     * data points belonging to this partition is the new centroid.
     */
    public double calculateCentroid() {
    	return fileList.avgFileSize();
    }

    /**
     * Clears the data records
     */
    public void reset() {
        // now clear the records
        fileList.removeAll();
    } 
}