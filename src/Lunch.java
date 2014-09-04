// ===============Let's Do Lunch (code written by: Sajjad Pourmohammad) ===============
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

public class Lunch {

	public static void main(String[] args) {		
		ArrayList<String> InputsList = new ArrayList<>();
		String currentline;
		int index=0;
		//==================== Read the input from a text file called input.txt ==============
		try {
			FileReader fr = new FileReader("input.txt");
			BufferedReader bf=new BufferedReader(fr);
			while ((currentline=bf.readLine())!=null) {
				InputsList.add(currentline);				
			}
			bf.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}		
		// =========== Find the line indexes of "Avoid:" and "Sam:" and "Peggy:" in the input
		int AvoidIndex=InputsList.indexOf("Avoid:");
		int PeggyIndex=InputsList.indexOf("Peggy:");
		int SamIndex=InputsList.indexOf("Sam:");		
		//======Processing the source and destination nodes=========
		String[] SourceNodes1=new String[AvoidIndex-1]; // Index 1 is for Peggy
		String[] DestNodes1=new String[AvoidIndex-1];
		String[] SourceNodes2=new String[AvoidIndex-1]; // Index 2 is for Sam
		String[] DestNodes2=new String[AvoidIndex-1];
		String[] SortedDest=new String[AvoidIndex-1]; // this is used to sort the source nodes for Sam
		for (index=1;index<AvoidIndex;index++){ //for items between Map: and Avoid:
			String[] LineString=InputsList.get(index).split(" ");			
			SourceNodes1[index-1]=LineString[0];  // Add the first element to the source nodes
			DestNodes1[index-1]=LineString[1];  // Add the second  element to the DestNodes	
			SortedDest[index-1]=DestNodes1[index-1]+" "+SourceNodes1[index-1];			
		}
		Arrays.sort(SortedDest); // Since Destination nodes are not necessarily sorted, SortedDest is used to sort them		
		for (index=0;index<SortedDest.length;index++){ 
			String[] LineString=SortedDest[index].split(" ");			
			SourceNodes2[index]=LineString[0];  // Source nodes in the map used by Sam
			DestNodes2[index]=LineString[1];		// Dest nodes in the map used by Sam			
		}		
		String[] AvoidList=InputsList.get(AvoidIndex+1).split(" ");  // Get the avoid list
		String[] PeggyStartsAt=InputsList.get(PeggyIndex+1).split(" "); // Peggy start points
		String[] SamStartsAt=InputsList.get(SamIndex+1).split(" "); // Sam start points		
		//============ Find the reachable set for Peggy ================
		TreeSet< String> PeggyReachableSet=ReachableSet(SourceNodes1,DestNodes1,AvoidList,PeggyStartsAt);
		TreeSet< String> SamReachableSet=ReachableSet(SourceNodes2,DestNodes2,AvoidList,SamStartsAt);		
		PeggyReachableSet.retainAll(SamReachableSet); // Find the common reachable set for Peggy and Sam
		for (String output:PeggyReachableSet){
			System.out.println(output);
		}		
	}
	//============================ An implementation of BFS to find the reachable set ==============
	public static TreeSet<String> ReachableSet(String[] SourceNodes, String[] DestNodes, String[] AvoidList,String[] StartsAt){
		TreeSet< String> ReachableSet=new TreeSet<String>();
		Queue<String> q=new LinkedList<String>();
		for (String s:StartsAt){q.add(s);}    // Add the root nodes to the queue
		while(!q.isEmpty()){
			String element =q.remove();
			if (!Arrays.asList(AvoidList).contains(element)){
				ReachableSet.add(element);
				int index=Arrays.asList(SourceNodes).indexOf(element);
				if (index!=-1) { // Since the input is sorted we just check the next lines to see if there is any child node
					q.add(DestNodes[index]);
					while (index<(SourceNodes.length-1)&&SourceNodes[index+1].equals(element)) {
						q.add(DestNodes[index+1]);
						index++;
					}					
				}				
			}
		}		
		return ReachableSet;		
	}
}
