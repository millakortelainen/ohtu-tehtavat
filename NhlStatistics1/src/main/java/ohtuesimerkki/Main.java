package ohtuesimerkki;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics( new PlayerReader("https://nhlstatisticsforohtu.herokuapp.com/players.txt") );
          
        System.out.println("Philadelphia Flyers");
        for (Player player : stats.team("PHI") ) {
            System.out.println( player );
        }
        
        System.out.println("");
        
        System.out.println("Top scorers");
        for (Player player : stats.topScorers(10) ) {
          for(i=0;i<10;i++){
              System.out.println(i);
          }
            System.out.println( player );
        }        
    }
}
