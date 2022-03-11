import java.util.ArrayList;
import java.util.HashMap;

class DeclarationInfo{
    String userID;
    String nominated;
    String explanation;
    public DeclarationInfo(String ID,String nomin){ 
		//System.out.println("생성자 호출 성공");
        this.userID = ID;
        this.nominated = nomin;
        this.explanation = ID+"가"+nomin+"를 신고했습니다.";
    }
}

class count{
    String userID;
    int count;
    public count(String ID,int ct){
        this.userID = ID;
        this.count = ct;
    }
}

class stopList{
    String userID;
    ArrayList<String> nominatedID = new ArrayList<String>();
    ArrayList<String> stoppedID = new ArrayList<String>();
    public stopList(String ID,ArrayList<String> nom,ArrayList<String> stop){
        this.userID = ID;
        this.nominatedID = nom;
        for(int i=0;i<nom.size();i++){
            if(stop.contains(nominatedID.get(i)) && !stoppedID.contains(nominatedID.get(i)))
                this.stoppedID.add(nominatedID.get(i));
        }
    }
}
    
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        ArrayList<Integer> answer = new ArrayList<Integer>();
        //int[] answer = {0,1,1,1};
        ArrayList<String> users = new ArrayList<String>();
        ArrayList<DeclarationInfo> map1 = new ArrayList<DeclarationInfo>();
        ArrayList<String> result = new ArrayList<String>();
        for(int i=0; i<report.length; i++) {
           String[] res = report[i].split(" ");
            for(int j=0; j<res.length; j++) {
                 if(!users.contains(res[j])) users.add(res[j]);
                 result.add(res[j]);
            }
        }
        //System.out.println(result.size());
        //System.out.println(users);
        for(int j=0; j<result.size()-1; j+=2) {
           //System.out.println(result.get(j));
           DeclarationInfo d = new DeclarationInfo(result.get(j),result.get(j+1));
           map1.add(d);
        }
    
        //확인
        // System.out.println("-------------map1-------------");
        // for(int i=0;i<map1.size();i++){
        //     System.out.println(map1.get(i).userID);
        //     System.out.println(map1.get(i).nominated);
        //     System.out.println(map1.get(i).explanation);
        // }
        
        ArrayList<count> map2 = new ArrayList<count>();
        ArrayList<Integer> cnt = new ArrayList<Integer>();
        ArrayList<String> stoppedID = new ArrayList<String>();
        //cnt 가져오기
        for(int g=0;g<users.size();g++){
            int c=0;
            ArrayList<String> list = new ArrayList<String>();
            //System.out.println("user:"+users.get(g));
            for(int h=0;h<map1.size();h++){
                //System.out.println(map1.get(h).nominated);
              if(users.get(g).equals(map1.get(h).nominated)){
                  if(c>0 && list.contains(map1.get(h).userID+map1.get(h).nominated)){
                      break;
                  }
                    c++;
                }
              list.add(map1.get(h).userID+map1.get(h).nominated);
              //System.out.println(list);
            }
            cnt.add(new Integer(c));
        }
        //System.out.println(cnt.get(0));
        //map2 완성
        for(int i=0;i<users.size();i++){
            count c = new count(users.get(i),cnt.get(i).intValue());
            map2.add(c);
        }
        
        //확인
        // System.out.println("-------------map2-------------");
        // for(int i=0;i<map2.size();i++){
        //     System.out.println(map2.get(i).userID);
        //     System.out.println(map2.get(i).count);
        // }
        
        //map3 만들어 보기
        ArrayList<stopList> map3 = new ArrayList<stopList>();
        for(int i=0;i<map2.size();i++){
            if(map2.get(i).count>=k){
                stoppedID.add(map2.get(i).userID);
            }
        }
        //System.out.println(stoppedID);
        for(int i=0;i<map2.size();i++){
            ArrayList<String> nominatedID = new ArrayList<String>();
            //System.out.println("user:"+map2.get(i).userID);
            for(int h=0;h<map1.size();h++){
                 if(map2.get(i).userID.equals(map1.get(h).userID)){
                    //System.out.println(map1.get(h).nominated);
                    nominatedID.add(map1.get(h).nominated);
                }
           }
           //System.out.println(nominatedID);
           stopList c = new stopList(users.get(i),nominatedID,stoppedID);
           map3.add(c);
        }

        //확인
        // System.out.println("-------------map3-------------");
        for(int i=0;i<map3.size();i++){
            // System.out.println(map3.get(i).userID);
            // System.out.println(map3.get(i).nominatedID);
            // System.out.println(map3.get(i).stoppedID);
            answer.add(new Integer(map3.get(i).stoppedID.size()));
        }
        
        //Integer ArrayList를 int 배열로
        int[] ret = new int[answer.size()];
            for (int i=0; i < ret.length; i++)
            {
                ret[i] = answer.get(i).intValue();
            }
        return ret;
    }
}