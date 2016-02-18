/**
 * Read web server data and analyse
 * hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 2011.07.31
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Array of persons
    private String[] persons;
    // Boolean Vacat
    private boolean[] vacant;   
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(String fileName)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(fileName);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        int hour = 0;
        
        while(hour != hourCounts.length) {
           
            System.out.println(hour + ": " + hourCounts[hour]);
            hour = hour + 1;
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    public int numberOfAccesses()
    {
    int total = 0;
    for (int index=0; index < hourCounts.length; index++){
        total = total + hourCounts[index];
    }
    return total;
    }
    
    
    public int busiestHour()
    {
        int highest = 0;
         int total = 0;
         for (int index=0; index < hourCounts.length; index++){
             
              if(hourCounts[index] > highest){
                  highest = hourCounts[index];
                }
            
            }
            
            return highest;
    }
    
      public int quietestHour()
    {
        int lowest = 555555555;
         int total = 0;
         for (int index=0; index < hourCounts.length; index++){
             
              if(hourCounts[index] < lowest){
                  lowest = hourCounts[index];
                }
            
            }
            
            return lowest;
    }
    
    public int twoHour()
    {
        int highest = 0;
         int total = 0;
         int firstPeriod = 0;
         for (int index=0; index < hourCounts.length; index++){
              int twoHours = 0;
              if(index + 1 != hourCounts.length){
                  twoHours = hourCounts[index] + hourCounts[index + 1];
                 // System.out.println("ADDING" + hourCounts[index] + ": " + hourCounts[index + 1] + " INDEX IS NOW " + index);
                }else{
                twoHours = hourCounts[index];
                }
              
            
              
              System.out.println(twoHours + ": " + highest);
              if(twoHours > highest){
                  highest = twoHours;
                  firstPeriod = hourCounts[index];
                 // System.out.println("FirstPeriod=" +  index);
                  index++;
                }else{
                 index++;
                }
         }
            
       return firstPeriod;
    }
    
    
    
    
}

