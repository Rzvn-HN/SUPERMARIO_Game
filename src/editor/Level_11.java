package editor;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.awt.Point;

public class Level_11 extends BasicLevel {
    public Level_11() {
        this.BackGroundColor = "Blue";
        this.Time = "400";
        this.type = "GreenAndTrees";
        this.pos = new Point(10, 12);
        this.BackGroundImage = "Mountain";
        this.attribute = "Ground";
        this.LevelLength = 6768;
        this.Add("stone", 0, 13, 69, 2);
        this.Add("stone", 71, 13, 15, 2);
        this.Add("stone", 89, 13, 63, 2);
        this.Add("stone", 155, 13, 156, 2);
        this.Add("pump", 28, 11, 1, 2);
        this.Add("pump", 38, 10, 1, 3);
        this.Add("pump", 46, 9, 1, 4);
        this.Add("pump", 57, 9, 1, 4);
        this.Add("Brick", 20, 9, 1, 1);
        this.Add("Brick", 22, 9, 1, 1);
        this.Add("Brick", 24, 9, 1, 1);
        this.Add("QuestionMarkWithMushroom", 16, 9, 1, 1);
        this.Add("QuestionMark", 21, 9, 1, 1);
        this.Add("QuestionMarkWithMushroom", 23, 9, 1, 1);
        this.Add("QuestionMark", 22, 5, 1, 1);
        this.AddInvisibleBrckWith1Up(64, 8);
        this.Add("Brick", 77, 9, 1, 1);
        this.Add("QuestionMarkWithMushroom", 78, 9, 1, 1);
        this.Add("Brick", 79, 9, 1, 1);
        this.Add("Brick", 80, 5, 8, 1);
        this.Add("Brick", 91, 5, 3, 1);
        this.Add("QuestionMark", 94, 5, 1, 1);
        this.AddBank(94, 9);
        this.Add("Brick", 100, 9, 1, 1);
        this.AddBrickWithStar(101, 9);
        this.Add("QuestionMark", 106, 9, 1, 1);
        this.Add("QuestionMark", 109, 9, 1, 1);
        this.Add("QuestionMark", 112, 9, 1, 1);
        this.AddQuestionMarkWithMushroom(109, 5);
        this.Add("Brick", 118, 9, 1, 1);
        this.Add("Brick", 121, 5, 3, 1);
        this.Add("Brick", 128, 5, 1, 1);
        this.Add("QuestionMark", 129, 5, 2, 1);
        this.Add("Brick", 131, 5, 1, 1);
        this.Add("Brick", 129, 9, 2, 1);
        this.Add("chocolate", 134, 12, 4, 1);
        this.Add("chocolate", 135, 11, 3, 1);
        this.Add("chocolate", 136, 10, 2, 1);
        this.Add("chocolate", 137, 9, 1, 1);
        this.Add("chocolate", 140, 9, 1, 1);
        this.Add("chocolate", 140, 10, 2, 1);
        this.Add("chocolate", 140, 11, 3, 1);
        this.Add("chocolate", 140, 12, 4, 1);
        this.Add("chocolate", 148, 12, 4, 1);
        this.Add("chocolate", 149, 11, 3, 1);
        this.Add("chocolate", 150, 10, 2, 1);
        this.Add("chocolate", 151, 9, 1, 1);
        this.Add("chocolate", 155, 9, 1, 1);
        this.Add("chocolate", 155, 10, 2, 1);
        this.Add("chocolate", 155, 11, 3, 1);
        this.Add("chocolate", 155, 12, 4, 1);
        this.Add("pump", 163, 11, 1, 2);
        this.Add("Brick", 168, 9, 2, 1);
        this.Add("QuestionMark", 170, 9, 1, 1);
        this.Add("Brick", 171, 9, 1, 1);
        this.Add("pump", 179, 11, 1, 2);
        this.Add("chocolate", 181, 12, 9, 1);
        this.Add("chocolate", 182, 11, 8, 1);
        this.Add("chocolate", 183, 10, 7, 1);
        this.Add("chocolate", 184, 9, 6, 1);
        this.Add("chocolate", 185, 8, 5, 1);
        this.Add("chocolate", 186, 7, 4, 1);
        this.Add("chocolate", 187, 6, 3, 1);
        this.Add("chocolate", 188, 5, 2, 1);
        this.Add("chocolate", 198, 12, 1, 1);
        this.AddEnemyMushroom(21, 12);
        this.Add("EnemyMushroom", 40, 12, 1, 1);
        this.Add("EnemyMushroom", 53, 12, 2, 1);
        this.Add("EnemyMushroom", 80, 4, 1, 1);
        this.Add("EnemyMushroom", 83, 4, 1, 1);
        this.Add("EnemyMushroom", 95, 12, 2, 1);
        this.Add("EnemyMushroom", 123, 12, 2, 1);
        this.Add("EnemyMushroom", 127, 12, 2, 1);
        this.Add("EnemyMushroom", 173, 12, 2, 1);
        this.Add("EnemyTurtle", 106, 11, 1, 1);
        this.AddCheckPointsFlag(198, 3);
        this.Add("SmallCastle", 202, 8, 1, 1);
        this.AddCheckPoints(205, 12, 12, new Point(2, 3));
    }
}
