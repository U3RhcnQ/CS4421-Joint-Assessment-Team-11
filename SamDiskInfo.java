public class SamDiskInfo {
    public static void main(String[] args){
        percentUsed();
        diskInformation();
    }
    public static void percentUsed(){
        diskInfo disk = new diskInfo();
        disk.read();
        for (int i = 0; i < disk.diskCount(); i++) {
            double percentageDisk = ((double)disk.getUsed(i) / disk.getTotal(i))*100;
            System.out.println("Percentage of Disk used is: " + percentageDisk + "%");
        }
    }
    public static void diskInformation(){
        diskInfo disk = new diskInfo();
        disk.read();
        // Iterate through all the disks
        for (int i = 0; i < disk.diskCount(); i++) {
            System.out.println("disk " + disk.getName(i) + " has " +
                    disk.getTotal(i) + " blocks, of which " +
                    disk.getUsed(i) + " are used");
        }
    }
}