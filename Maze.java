import java.util.Scanner;

public class Maze {
    
    static char[][] maze;
    
    static int playerX, playerY;
    static int exitX, exitY;
    
    static int maxMoves;
    static int movesMade;
    
    static int maxViews;
    static int viewsUsed;
    
    static int score;
    static int itemsCollected;
    
    static Scanner scanner = new Scanner(System.in);
    
    static boolean playing = true;
    
    public static void main(String[] args) {
        
        movesMade = 0;
        viewsUsed = 0;
        score = 0;
        itemsCollected = 0;
        
        while (playing) {
            System.out.println("---------------------------------------------------");
            System.out.println("Welcome to the Maze Game!");
            System.out.println(" (1) - Play ");
            System.out.println(" (2) - Rules ");
            System.out.println(" (3) - Credits ");
            System.out.println(" (4) - Exit ");
            System.out.println("---------------------------------------------------");
            
            String inputOption = scanner.nextLine();
        
            switch (inputOption) {
                case "1":
                    startGame();
                    break;
                case "2":
                    showRules();
                    break;
                case "3":
                    showCredits();
                    break;
                case "4":
                    System.out.println("GOODBYE!");
                    playing = false;
                    break;
                default:
                    System.out.println("Invalid value! Please enter a number from 1 to 4.");
                    playing = true;
            }
        }
    }
    
    public static void startGame() {
        System.out.println("----------------------------------------------------");
        System.out.println(" Select the difficulty by entering the corresponding number:");
        System.out.println(" (1) - Easy -> 10x10 ");
        System.out.println(" (2) - Medium -> 20x20 ");
        System.out.println(" (3) - Hard -> 30x30 ");
        System.out.println(" (4) - Back to menu ");
        System.out.println("----------------------------------------------------");
        
        String difficultyOption = scanner.nextLine();
        
        switch (difficultyOption) {
            case "1":
                easyMap();
                break;
            case "2":
                mediumMap();
                break;
            case "3":
                hardMap();
                break;
            case "4":
                playing = true;
                return;
            default:
                System.out.println("Invalid value! Please enter a number from 1 to 4.");
                startGame();
                return;
        }
        
        boolean activeGame = true;
        while (activeGame) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Move (W/A/S/D)");
            System.out.println("2. View maze (" + (maxViews - viewsUsed) + " remaining)");
            System.out.println("3. Quit game");
            System.out.print("Choose an option: ");
            String option = scanner.nextLine();
            
            switch (option) {
                case "1":
                    System.out.print("Enter direction (W/A/S/D): ");
                    char direction = scanner.next().toUpperCase().charAt(0);
                    scanner.nextLine(); // Consume remaining newline
                    movePlayer(direction);
                    break;
                case "2":
                    if (viewsUsed < maxViews) {
                        displayMaze();
                        viewsUsed++;
                        System.out.println("Views used: " + viewsUsed + "/" + maxViews);
                    } else {
                        System.out.println("You have already used all available views!");
                    }
                    break;
                case "3":
                    activeGame = false;
                    System.out.println("You gave up on the game!");
                    break;
                default:
                    System.out.println("Invalid value! Choose a number from 1 to 3.");
            }
            
            if (playerX == exitX && playerY == exitY) {
                activeGame = false;
                System.out.println("\nCONGRATULATIONS! You found the exit!");
                calculateScore();
                displayFinalResult();
            } else if (movesMade >= maxMoves) {
                activeGame = false;
                System.out.println("\nGAME OVER! You ran out of moves!");
                displayFinalResult();
            }
        }
    }
    
    public static void showRules() {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("Welcome to the maze rules!");
        System.out.println("- You have a limited number of moves and maze views.");
        System.out.println("- Inside the maze, there are collectible items that give points or increase allowed views.");
        System.out.println("- If you run out of moves, you lose.");
        System.out.println("- Type (1) to return to the menu.");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        String backMenu = scanner.nextLine();
        
        if (backMenu.equals("1")) {
            playing = true;
        } else { 
            System.out.println("Invalid value!");
            showRules();
        }
    }
    
    public static void showCredits() {
        System.out.println("Project Participants:");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("Antonio Fernando Alcantara Neto");
        System.out.println("Enzo Amaral Santos");
        System.out.println("José Rafael Leite");
        System.out.println("Pedro Paulo Costa da Silva");
        System.out.println("Yago Sá Lobão");
        System.out.println("Yuri Pereira Vieira Evandro");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("Type (1) to go to the menu");
        System.out.println("Type (2) to exit the program");

        String creditOption = scanner.nextLine();
        if (creditOption.equals("1")) {
            playing = true;
        } else { 
            if (creditOption.equals("2")) {
                playing = false;
            } else {
                System.out.println("Invalid value! Enter 1 or 2.");
                showCredits();
            }
        }
    }
    
    public static void easyMap() {
        System.out.println("Would you like to customize your game? Type (1) for yes or (2) for no: ");
        String option = scanner.nextLine();
        switch (option) {
            case "1":
                System.out.print("Enter the maximum number of moves: ");
                maxMoves = scanner.nextInt();
                System.out.print("Enter the maximum number of maze views: ");
                maxViews = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                break;
            case "2":
                maxMoves = 20;
                maxViews = 10;
                break;
            default:
                System.out.println("Invalid value! Enter 1 or 2.");
                easyMap();
                break;
        }
        maze = new char[][] {
            {'P', '.', '.', '#', '#', '.', '.', '.', '.', '.'},
            {'#', '#', '.', '#', '.', '.', '#', '#', '#', '.'},
            {'.', '.', '.', '.', '#', '.', '.', '.', '#', '.'},
            {'.', '#', '#', '.', '#', '#', '#', '.', '.', '#'},
            {'.', '#', '.', '.', '.', '.', '.', '.', '#', '#'},
            {'.', '#', '.', '#', '#', '#', '.', '#', '#', '.'},
            {'.', '.', '.', '#', '.', '.', '.', '.', '.', '.'},
            {'#', '#', '.', '.', '.', '#', '#', '.', '#', '.'},
            {'.', '.', '#', '#', '.', '.', '#', '.', '.', '.'},
            {'.', '.', '.', '.', '#', '#', '#', '#', '#', 'S'}
        };
        
        maze[1][5] = '*';
        maze[4][4] = '*';
        maze[7][3] = '*';
        findInitialPositions();
    }    

    public static void mediumMap() {
        System.out.println("Would you like to customize your game? Type (1) for yes or (2) for no: ");
        String option = scanner.nextLine();
        switch (option) {
            case "1":
                System.out.print("Enter the maximum number of moves: ");
                maxMoves = scanner.nextInt();
                System.out.print("Enter the maximum number of maze views: ");
                maxViews = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                break;
            case "2":
                maxMoves = 50;
                maxViews = 10;
                break;
            default:
                System.out.println("Invalid value! Enter 1 or 2.");
                mediumMap();
                break;
        }
        maze = new char[][] {
            {'#','.','.','.','#','#','#','.','.','.','.','.','#','#','#','.','.','.','#','#'},
            {'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','S'},
            {'.','.','#','#','#','#','#','.','#','#','#','#','#','#','#','#','#','#','.','.'},
            {'.','.','#','.','.','.','#','.','.','.','.','.','.','.','.','.','#','#','.','.'},
            {'.','.','#','.','#','.','#','#','#','#','#','#','#','.','#','.','.','#','.','.'},
            {'#','.','#','.','#','.','*','.','.','.','.','.','#','.','#','.','#','#','.','#'},
            {'#','.','#','.','#','#','#','#','#','#','.','.','#','.','#','.','.','.','.','#'},
            {'#','.','#','.','.','.','.','.','.','#','.','.','#','#','#','#','#','.','.','#'},
            {'.','.','#','#','#','#','#','#','.','#','.','.','.','.','#','.','#','#','#','#'},
            {'.','.','.','.','.','.','.','#','.','#','#','#','.','#','#','.','.','.','#','.'},
            {'#','#','#','#','#','.','.','#','.','.','.','#','.','.','.','.','#','.','#','.'},
            {'.','.','.','.','#','.','#','#','#','#','.','#','#','#','.','#','.','#','.'},
            {'.','.','#','#','#','.','.','.','.','#','.','.','.','.','#','.','.','.','#','.'},
            {'.','.','#','.','.','#','#','.','#','.','#','#','#','#','.','#','#','#','#','#'},
            {'.','#','#','#','#','#','#','.','#','#','#','#','#','.','.','.','.','.','.','.'},
            {'.','P','.','.','.','.','.','.','.','.','.','.','.','#','#','#','#','#','.','.'},
            {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','.','.','.','.','.','.'},
            {'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
            {'#','.','.','.','#','#','#','.','#','#','.','.','.','#','#','#','#','.','.','.'}
        };
        maze[8][15] = '*';
        maze[1][9] = '*';
        maze[8][4] = '*';
        maze[19][7] = '*';
        findInitialPositions();
    }

    public static void hardMap() {
        System.out.println("Would you like to customize your game? Type (1) for yes or (2) for no: ");
        String option = scanner.nextLine();
        switch (option) {
            case "1":
                System.out.print("Enter the maximum number of moves: ");
                maxMoves = scanner.nextInt();
                System.out.print("Enter the maximum number of maze views: ");
                maxViews = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                break;
            case "2":
                maxMoves = 140;
                maxViews = 10;
                break;
            default:
                System.out.println("Invalid value! Enter 1 or 2.");
                hardMap();
                break;
        }
        maze = new char[][] {
            {'#','.','.','.','.','#','#','#','#','.','.','.','#','#','.','.','#','.','.','.','.','#','#','#','.','.','.','.','#','.'},
            {'.','P','.','.','#','.','.','.','.','.','.','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','.'},
            {'.','#','#','.','#','.','#','#','#','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
            {'#','.','.','.','#','.','#','.','.','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','.','.'},
            {'#','.','#','#','#','.','#','.','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#','.','.'},
            {'#','.','#','.','.','.','#','.','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','.','.','#','.','.'},
            {'.','.','#','.','#','#','#','.','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#','.','#','.','#','#'},
            {'.','.','#','.','#','.','.','.','#','.','#','#','#','#','#','#','#','#','#','#','#','#','#','.','#','.','#','.','.','#'},
            {'.','.','#','.','#','.','#','#','#','.','#','.','.','.','.','.','.','.','.','.','.','.','#','.','#','.','#','#','.','#'},
            {'.','.','#','.','#','.','.','.','.','.','#','.','#','#','#','#','#','#','#','#','#','.','#','.','#','.','.','.','.','#'},
            {'.','.','#','#','#','#','#','#','#','#','#','.','#','.','.','.','.','.','.','.','#','.','#','.','#','#','#','#','.','.'},
            {'#','.','.','.','.','.','.','.','.','.','.','.','#','.','#','#','#','#','#','.','#','.','#','.','.','.','.','#','.','.'},
            {'#','#','#','#','#','#','#','#','#','#','#','#','#','.','#','.','.','.','#','.','#','.','#','#','#','.','#','.','#','.'},
            {'#','.','.','.','.','.','.','.','.','.','.','.','.','.','#','.','#','.','#','.','#','.','.','#','.','.','.','.','.','#'},
            {'#','.','#','#','#','#','#','#','#','#','#','#','#','#','#','.','#','.','#','.','#','#','.','#','.','#','#','#','.','.'},
            {'#','.','#','.','.','.','.','.','.','.','.','.','.','.','.','.','#','.','#','.','.','.','.','.','.','.','.','#','.','.'},
            {'#','.','#','.','#','#','#','#','#','#','#','#','#','#','#','#','#','.','#','#','#','#','#','#','#','#','.','#','.','.'},
            {'#','.','#','.','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#','.','#','.','#','.'},
            {'#','.','#','.','#','.','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','.','#','.','#','.','.','.'},
            {'#','.','#','.','#','.','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#','.','#','.','#','#','.','.'},
            {'.','.','#','.','#','.','#','.','#','#','#','#','#','#','#','#','#','#','#','#','#','.','#','.','#','.','.','.','.','#'},
            {'.','.','#','.','#','.','#','.','#','.','.','.','.','.','.','.','.','.','.','.','#','.','#','.','#','#','#','#','.','#'},
            {'#','.','#','.','#','.','#','.','#','.','#','#','#','#','#','#','#','#','#','.','#','.','#','.','.','.','.','#','.','.'},
            {'#','.','#','.','#','.','#','.','#','.','#','.','.','.','.','.','.','.','#','.','#','.','#','#','#','.','#','.','#','.'},
            {'#','.','#','.','#','.','#','.','#','.','#','.','#','#','#','#','.','.','#','.','#','.','.','.','.','.','.','.','.','#'},
            {'.','.','#','.','#','.','#','.','#','.','#','.','#','.','.','#','.','.','#','.','#','#','#','#','.','#','#','#','.','.'},
            {'.','.','#','.','#','.','#','.','#','.','#','.','#','.','#','#','.','.','#','.','.','.','.','.','.','.','.','.','.','.'},
            {'.','.','#','.','#','.','#','.','#','.','#','.','#','.','#','#','.','.','#','#','#','#','#','.','#','#','#','#','.','.'},
            {'#','.','#','.','#','.','#','.','#','.','#','.','#','.','#','.','#','#','#','.','.','.','.','.','.','.','.','#','.','.'},
            {'#','.','.','.','#','.','#','.','#','.','#','.','#','.','#','.','.','.','.','#','#','#','#','#','#','.','S','#','.','#'}
        };
        maze[11][26] = '*';
        maze[13][4] = '*';
        maze[17][2] = '*';
        maze[22][29] = '*';
        findInitialPositions();
    }
    
    public static void findInitialPositions() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 'P') {
                    playerY = i;
                    playerX = j;
                    maze[i][j] = '@';
                } else if (maze[i][j] == 'S') {
                    exitY = i;
                    exitX = j;
                }
            }
        }
    }
    
    public static void movePlayer(char direction) {
        int newX = playerX;
        int newY = playerY;
        
        switch (direction) {
            case 'W': 
                newY--;
                break;
            case 'A':
                newX--;
                break;
            case 'S': 
                newY++;
                break;
            case 'D': 
                newX++;
                break;
            default:
                System.out.println("Invalid direction! Use only W, A, S, or D.");
                return;
        }
        
        if (newX < 0 || newX >= maze[0].length || newY < 0 || newY >= maze.length) {
            System.out.println("Invalid move! You cannot leave the maze.");
        } else if (maze[newY][newX] == '#') {
            System.out.println("Invalid move! There is a wall in the way.");
        } else {
            maze[playerY][playerX] = '.'; 
            
            if (maze[newY][newX] == '*') {
                itemsCollected++;
                score += 50;
                System.out.println("You collected an item! +50 points");
            }
            
            playerX = newX;
            playerY = newY;
            maze[playerY][playerX] = '@'; 
            
            movesMade++;
            System.out.println("Moves made: " + movesMade + "/" + maxMoves);
        }
    }
    
    public static void displayMaze() {
        System.out.println("\n=== MAZE ===");
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void calculateScore() {
        int remainingMoves = maxMoves - movesMade;
        score += remainingMoves * 10;
        score += (maxViews - viewsUsed) * 20;
    }
    
    public static void displayFinalResult() {
        System.out.println("\n=== FINAL RESULT ===");
        System.out.println("Moves made: " + movesMade);
        System.out.println("Views used: " + viewsUsed);
        System.out.println("Items collected: " + itemsCollected);
        System.out.println("Final score: " + score);
    }
}