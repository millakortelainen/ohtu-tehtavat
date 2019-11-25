package ohtu;

public class TennisGame {

    private int player1Score;
    private int player2Score;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.player1Score = 0;
        this.player2Score = 0;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(this.player1Name)) {
            this.player1Score++;
        } else {
            this.player2Score++;
        }
    }

    public String getScore() {

        if (player1Score == player2Score) {
            return tieBetweenPlayers();
        }
        if (player1Score >= 4 || player2Score >= 4) {
            return resultBetweenPlayers();
        }
        return scoreChecker(player1Score) + "-" + scoreChecker(player2Score);
    }

    public String scoreChecker(int score) {
        switch (score) {
        case 0:
            return "Love";
        case 1:
            return "Fifteen";
        case 2:
            return "Thirty";
        case 3:
            return "Forty";
        }
        return "";
    }

    public String tieBetweenPlayers() {
        if (scoreChecker(this.player1Score).isEmpty()) {
            return "Deuce";
        }
        return scoreChecker(this.player1Score) + "-All";
    }

    public String resultBetweenPlayers() {
        int minusResult = player1Score - player2Score;
        if (minusResult == 1) {
            return "Advantage player1";
        }
        if (minusResult == -1) {
            return "Advantage player2";
        }
        if (minusResult >= 2) {
            return "Win for player1";
        }
        return "Win for player2";
    }

}