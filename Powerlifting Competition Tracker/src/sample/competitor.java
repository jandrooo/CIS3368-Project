package sample;

public class competitor {
        private double bodyWeight, classWeight, benchPress, squat, deadlift;
        private String competitorName, teamName, division, bpChecker, sChecker, dlChecker;

        //default constructor
        public competitor(){
            this.competitorName = "";
            this.teamName = "";
            this.division = "";
            this.bodyWeight = 0;
            this.classWeight = 0;
            this.benchPress = 0;
            this.squat = 0;
            this.deadlift = 0;
            this.bpChecker = "";
            this.sChecker = "";
            this.dlChecker = "";
        }

        public competitor(String competitorName, String teamName, String division, double bodyWeight, double classWeight, double benchPress, double squat, double deadlift, String bpChecker, String sChecker, String dlChecker){
            this.competitorName = competitorName;
            this.teamName = teamName;
            this.division = division;
            this.bodyWeight = bodyWeight;
            this.classWeight = classWeight;
            this.benchPress = benchPress;
            this.squat = squat;
            this.deadlift = deadlift;
            this.bpChecker = bpChecker;
            this.sChecker = sChecker;
            this.dlChecker = dlChecker;
        }

        //getters
        public double getBodyWeight() {
            return bodyWeight;
        }

        public double getClassWeight() {
            return classWeight;
        }

        public double getBenchPress() {
            return benchPress;
        }

        public double getSquat() {
            return squat;
        }

        public double getDeadlift() {
            return deadlift;
        }

        public String getCompetitorName() {
            return competitorName;
        }

        public String getTeamName() {
            return teamName;
        }

        public String getDivision() {
            return division;
        }

        //set competitor name
        public void setCompetitorName(String competitorName) {
            this.competitorName = competitorName;
        }

        //set team name
        public void setTeamName(String teamName){
            this.teamName = teamName;
        }

        //set division
        public void setDivision(String division){
            this.division = division;
        }

        //Set body weight
        public void setBodyWeight(double bodyWeight) {
            this.bodyWeight = bodyWeight;
        }

        //set class weight
        public void setClassWeight(double classWeight){
            this.classWeight = classWeight;
        }

        //set bench press
        public competitor setBenchPress(double benchPress) {
            this.benchPress = benchPress;
            return null;
        }

        public void setSquat(double squat) {
            this.squat = squat;
        }

        public void setDeadlift(double deadlift) {
            this.deadlift = deadlift;
        }

        public String getBpChecker() {
            return bpChecker;
        }

        public void setBpChecker(String bpChecker) {
            this.bpChecker = bpChecker;
        }

        public String getSChecker() {
            return sChecker;
        }

        public void setSChecker(String sChecker) {
            this.sChecker = sChecker;
        }

        public String getDlChecker() {
            return dlChecker;
        }

        public void setDlChecker(String dlChecker) {
            this.dlChecker = dlChecker;
        }
}
