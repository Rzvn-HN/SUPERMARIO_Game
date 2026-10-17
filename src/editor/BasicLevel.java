package editor;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import java.awt.Point;
import java.util.Random;

public abstract class BasicLevel {
    public int LevelLength;
    public String BackGroundColor;
    public String Time;
    public String type;
    public Point pos;
    public String BackGroundImage;
    public String attribute;
    public boolean Bombs = false;
    public int BombsTurnOff = -1;
    public boolean FlyingFishes = false;
    public int FlyingFishesLength = -1;
    public Construct[] constructors = new Construct[5000];
    CheckPoints[] checkPoints = new CheckPoints[20];
    public int Items = 0;
    public int checkPoints_Amount = 0;
    public int Teleport_Items = 0;
    public String level_Name = "normal";

    public BasicLevel() {
    }

    public int Get_CheckPoints_Amount() {
        return this.constructors.length;
    }

    public CheckPoints get_CheckPoints_Number(int i) {
        return this.checkPoints[i];
    }

    public int Get_Items_Amount() {
        return this.constructors.length;
    }

    public Construct get_Item_Number(int i) {
        return this.constructors[i];
    }

    public void AddCheckPoints(int x, int y, int nextlevel, Point LOC) {
        CheckPoints tempcheckPoints = new CheckPoints((double)(x * 32 - 4), (double)(y * 32), nextlevel, LOC);
        this.checkPoints[this.checkPoints_Amount] = tempcheckPoints;
        ++this.checkPoints_Amount;
    }



    public void Add(String type, int x, int y, int LenX, int LenY, int Length) {
        Construct temp = new Construct();
        temp.setItem_Type(type);
        temp.setX(x);
        temp.setY(y);
        temp.setLength_X(LenX);
        temp.setLength_Y(LenY);
        temp.setPatrolLength(Length);
        this.constructors[this.Items] = temp;
        ++this.Items;
    }

    public void Add(String type, int x, int y, int LenX, int LenY) {
        Construct temp = new Construct();
        temp.setItem_Type(type);
        temp.setX(x);
        temp.setY(y);
        temp.setLength_X(LenX);
        temp.setLength_Y(LenY);
        this.constructors[this.Items] = temp;
        ++this.Items;
    }

    public void Add(String type, int x, int y, int LenX, int LenY, String rotation) {
        Construct temp = new Construct();
        temp.setItem_Type(type);
        temp.setX(x);
        temp.setY(y);
        temp.setLength_X(LenX);
        temp.setLength_Y(LenY);
        temp.ExtraInfo = rotation;
        this.constructors[this.Items] = temp;
        ++this.Items;
    }


    public void AddCastle(int x, int y, String size) {
        if ("small".equals(size)) {
            this.Add("SmallCastle", x, y, 1, 1);
        } else if ("big".equals(size)) {
            this.Add("BigCastle", x, y, 1, 1);
        }

    }

    public void AddBrick(int x, int y, int height, int EndAt) {
        this.Add("Brick", x, y, EndAt - x, height);
    }

    public void AddQuestionMark(int x, int y, int height, int EndAt) {
        this.Add("QuestionMark", x, y, EndAt - x, height);
    }

    public void AddQuestionMark(int x, int y) {
        this.Add("QuestionMark", x, y, 1, 1);
    }

    public void AddBrickWith1UP(int x, int y) {
        this.Add("BrickWith1UP", x, y, 1, 1);
    }

    public void AddBrickWithCoin(int x, int y) {
        this.Add("BrickWithCoin", x, y, 1, 1);
    }

    public void AddEnemyMushroom(int x, int y) {
        this.Add("EnemyMushroom", x, y, 1, 1);
    }

    public void AddWall(int x, int y, int height, int EndAt) {
        this.Add("Wall", x, y, EndAt - x, height);
    }

    public void AddRocketLauncher(int x, int y, int height) {
        this.Add("RocketLauncher", x, y, 1, height);
    }

    public void AddFlyingTurtle(int x, int y) {
        this.Add("FlyingTurtle", x, y, 1, 1);
    }

    public void AddPump(int x, int y, int height) {
        this.Add("pump", x, y, 1, height);
    }

    public void AddPumpWarp(int x, int y, int height) {
        this.Add("PumpWarp", x, y, 1, height);
    }

    public void AddChocolate(int x, int y) {
        this.Add("chocolate", x, y, 1, 1);
    }

    public void AddChocolate(int x, int y, int height, int EndAt) {
        this.Add("chocolate", x, y, EndAt - x, height);
    }

    public void AddBrick(int x, int y) {
        this.Add("Brick", x, y, 1, 1);
    }

    public void AddBrickWithMushroom(int x, int y) {
        this.Add("BrickWithMushroom", x, y, 1, 1);
    }

    public void AddMonkey(int x, int y) {
        this.Add("Monkey", x, y, 1, 1);
    }

    public void AddTurtle(int x, int y) {
        this.Add("EnemyTurtle", x, y, 1, 1);
    }

    public void AddHelmet(int x, int y) {
        this.Add("Helmet", x, y, 1, 1);
    }

    public void AddBank(int x, int y) {
        this.Add("Bank", x, y, 1, 1);
    }

    public void AddStone(int x, int y, int height, int EndAt) {
        this.Add("stone", x, y, EndAt - x, height);
    }

    public void AddFireBar(int x, int y, int size) {
        if (size == 6) {
            this.Add("FireBar", x, y, 1, 1, "CW");
        } else if (size == 12) {
            this.Add("BigFireBar", x, y, 1, 1, "CW");
        }

    }

    public void AddFireBar(int x, int y, int size, String rotate) {
        if (size == 6) {
            this.Add("FireBar", x, y, 1, 1, rotate);
        } else if (size == 12) {
            this.Add("BigFireBar", x, y, 1, 1, rotate);
        }

    }

    public void AddSonOfABuitch(int x, int y) {
        this.Add("SonOfABuitch", x, y, 1, 1);
    }

    public void AddBouncer(int x, int y) {
        this.Add("Bouncer", x, y + 1, 1, 1);
    }

    public void AddCheckPointsFlag(int x, int y) {
        this.Add("Flag", x, y, 1, 1);
    }

    public void AddAxe(int x, int y) {
        this.Add("Axe", x, y, 1, 1);
    }

    public void AddWhyYouDOThis(int x, int y) {
        this.Add("WhyYouDOThis", x, y, 1, 1);
    }

    public void AddBoss(int x, int y, int maxx) {
        this.Add("Boss", x, y, 1, 1, maxx);
    }

    public void AddBossHammer(int x, int y, int maxx) {
        this.Add("BossHammer", x, y, 1, 1, maxx);
    }

    public void AddBridgeBloks(int x, int y, int height, int EndAt) {
        this.Add("BridgeBloks", x, y, EndAt - x, height);
    }

    public void AddInvisibleBrckWith1Up(int x, int y) {
        this.Add("InvisibleBrckWith1Up", x, y, 1, 1);
    }

    public void AddBrickWithStar(int x, int y) {
        this.Add("BrickWithStar", x, y, 1, 1);
    }

    public void AddQuestionMarkWithMushroom(int x, int y) {
        this.Add("QuestionMarkWithMushroom", x, y, 1, 1);
    }

    public void AddCoin(int x, int y) {
        this.Add("Coin", x, y, 1, 1);
    }

    public void AddCoin(int x, int y, int height, int EndAt) {
        this.Add("Coin", x, y, EndAt - x, height);
    }

    public void AddIron(int x, int y) {
        this.Add("Iron", x, y, 1, 1);
    }

    public void AddInvisibleBrckWithCoin(int x, int y) {
        this.Add("InvisibleBrckWithCoin", x, y, 1, 1);
    }

    public void AddFire(int x, int y) {
        this.Add("BossFire", x, y, 1, 1);
    }

    public void AddLava(int x, int Length) {
        this.Add("Lava", x, 13, Length - x, 1);
    }

    public void AddLavaUP(int x, int Length) {
        this.Add("Lava", x, 12, Length - x, 1);
    }

    public void AddHoriImage(int x, int y) {
        this.Add("HoriImage", x, y, 1, 1);
    }

    public void AddPumpImage(int x, int y, int height) {
        this.Add("PumpImage", x, y, 1, height);
    }


    public void AddLift(int x, int y) {
        this.Add("Lift", x, y, 1, 1, 6);
    }

    public void AddEnemyTurtlePatrol(int x, int y, int Length) {
        this.Add("EnemyTurtlePatrol", x, y, 1, 1, Length);
    }

    public void AddFlyingTurtlePatrol(int x, int y, int Length) {
        this.Add("FlyingTurtlePatrol", x, y, 1, 1, Length);
    }

    public void AddLift_UpDown(int x, int y) {
        this.Add("Lift_UpDown", x, y, 1, 1, 6);
    }

    public void AddLift_UpDown(int x, int y, int Points) {
        this.Add("Lift_UpDown", x, y, 1, 1, Points);
    }

    public void AddLift_LeftRight(int x, int y) {
        this.Add("Lift_LeftRight", x, y, 1, 1, 6);
    }

    public void AddLift_LeftRight(int x, int y, int Points) {
        this.Add("Lift_LeftRight", x, y, 1, 1, Points);
    }

    public void AddLift_LeftRightInvert(int x, int y) {
        this.Add("Lift_LeftRightInvert", x, y, 1, 1, 6);
    }

    public void AddLift_LeftRightInvert(int x, int y, int Points) {
        this.Add("Lift_LeftRightInvert", x, y, 1, 1, Points);
    }

    public void AddLiftUP(int x, int y) {
        this.Add("LiftUP", x, y, 1, 1, 6);
    }

    public void AddLiftUP(int x, int y, int Points) {
        this.Add("LiftUP", x, y, 1, 1, Points);
    }

    public void AddLiftDown(int x, int y) {
        this.Add("LiftDown", x, y, 1, 1, 6);
    }

    public void AddLiftDown(int x, int y, int Points) {
        this.Add("LiftDown", x, y, 1, 1, Points);
    }

    public void AddLiftCar(int x, int y) {
        this.Add("LiftCar", x, y, 1, 1, 6);
    }

    public void AddLiftFall(int x, int y) {
        this.Add("LiftFall", x, y, 1, 1, 6);
    }

    public void AddLiftFall(int x, int y, int Points) {
        this.Add("LiftFall", x, y, 1, 1, Points);
    }

    public void AddStone(int x, int y) {
        this.Add("stone", x, y, 1, 1);
    }

    public void AddOctoPussy(int x, int y) {
        this.Add("OctoPussy", x, y, 1, 1);
    }

    public void AddFishGrey(int x, int y) {
        Random r = new Random();
        if (r.nextBoolean()) {
            this.Add("FishGrey", x, y, 1, 1);
        } else {
            this.Add("FishGreyUpDown", x, y, 1, 1);
        }

    }

    public void AddFishRed(int x, int y) {
        Random r = new Random();
        if (r.nextBoolean()) {
            this.Add("FishRed", x, y, 1, 1);
        } else {
            this.Add("FishRedUpDown", x, y, 1, 1);
        }

    }

    public void AddFishGreyUpDown(int x, int y) {
        this.Add("FishGreyUpDown", x, y, 1, 1);
    }

    public void AddFishRedUpDown(int x, int y) {
        this.Add("FishRedUpDown", x, y, 1, 1);
    }

    public void AddTree(int x, int y, int height, int EndAt) {
        this.Add("tree", x, y, EndAt - x, height);
    }

    public void AddWoodenBridge(int x, int y, int EndAt) {
        this.Add("WoodenBridge", x, y, EndAt - x, 1);
    }

    public void AddLavaBall(int x) {
        this.Add("LavaBall", x, 6, 1, 1);
    }

    public void AddWhiteLine(int x) {
        this.Add("WhiteLine", x, 2, 1, 1);
    }

    public void AddWater(int x, int y, int EndAt) {
        this.Add("Water", x, y, EndAt - x, 1);
    }

    public void AddBalenceLift(int x, int y, int Distance) {
        Construct temp = new Construct();
        temp.setItem_Type("BalenceLift");
        temp.setX(x);
        temp.setY(y);
        temp.setLength_X(1);
        temp.setLength_Y(1);
        temp.setPatrolLength(1);
        temp.setBridgeLength(Distance);
        this.constructors[this.Items] = temp;
        ++this.Items;
    }
}

