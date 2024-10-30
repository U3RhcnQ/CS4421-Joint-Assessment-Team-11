
public class SamDiskInfo {
    public static void main(String[] args){
        System.loadLibrary("sysinfo");
        diskInfo disk = new diskInfo();
        //disk.read();
        diskTable();

    }

    public static String[][] diskTable(){
        diskInfo disk = new diskInfo();
        disk.read();
        String[][] diskInfoArray = new String[disk.diskCount()][4]; // Creates the 2d list
        for (int i = 0; i < disk.diskCount(); i++) {
            diskInfoArray[i][0] = disk.getName(i); // Adds the disk names to the first row
            diskInfoArray[i][1] = String.valueOf((double) disk.getTotal(i) / 1048576) ; // Adds the total for each disk to the second row
            diskInfoArray[i][2] = String.valueOf((double) disk.getUsed(i) / 1048576); // Adds the used storage to the third row
            diskInfoArray[i][3] = String.valueOf(((double)(disk.getUsed(i) / disk.getTotal(i)))*100) ; // Adds the percent used to the fourth row
        }
        return diskInfoArray; 
    }

    public static String[][] diskTable2(){ // Nice Formatted Version
        diskInfo disk = new diskInfo();
        String[][] diskInfoArray = new String[disk.diskCount()][4]; // Creates the 2d list
        for (int i = 0; i < disk.diskCount(); i++) {
            diskInfoArray[i][0] = disk.getName(i); // Adds the disk names to the first row
            diskInfoArray[i][1] = String.format("%.2f",(double) disk.getTotal(i) / 1048576) + " GB"; // Adds the total for each disk to the second row
            diskInfoArray[i][2] = String.format("%.2f",(double) disk.getUsed(i) / 1048576) + " GB"; // Adds the used storage to the third row
            diskInfoArray[i][3] = String.format("%.2f",((double) disk.getUsed(i) / disk.getTotal(i)) * 100) + " %"; // Adds the percent used to the fourth row
        }
        return diskInfoArray;
    }
}