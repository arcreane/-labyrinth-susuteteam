package projet.labyrinth;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Map.entry;

public class MainController {

    @FXML
    private Button exitBtn;

    @FXML
    private Button gamemodeBtn;

    @FXML
    private Button menuBtn;

    @FXML
    private AnchorPane rootAll;

    @FXML
    private AnchorPane rootMain;

    @FXML
    private ToolBar rootMenu;

    @FXML
    private Button scorringBtn;

    @FXML
    private Button solutionBtn;

    Stage stage ;

    @FXML
    void exitAction(ActionEvent event) {
       stage= (Stage) rootAll.getScene().getWindow();
        stage.close();

    }

    @FXML
    void launchgame(ActionEvent event) throws IOException {

        rootMain.getChildren().setAll((Node) FXMLLoader.load(HelloApplication.class.getResource("start-view.fxml")));
    }

    @FXML
    void launchSolution(ActionEvent event) {

    }
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void A_Maze_Ing(int table_length) {

        // creation of the table that represent the amazing maze
        String[][] table = new String[table_length][table_length];

        int random_wall1 = 0;
        int random_wall2 = 0;

        int number_of_entries = 2;

        // That part initialize the table with "x" everywhere
        for (int x = 0; x < table.length; x++) {
            for (int y = 0; y < table[x].length; y++) {
                table[x][y] = "x";
            }
        }

        // That part put the middle to "0"
        for(int x = 1; x < (table.length - 1); x++){
            for (int y = 1; y < (table[x].length -1) ; y++) {
                table[x][y]= "x";
            }
        }
        int side1 = (int) (Math.random() * 4);
        int side2 = 0;
        String[] my_coords1 = side_to_coordinates(side1,table).split(":");
        String[] my_coords2;
        if (side1 == 0){
            side2 = 1;
            my_coords2 = side_to_coordinates(side2,table).split(":");
            System.out.println(my_coords1[0] + " " + my_coords1[1]); /* coordinates of entry */
            System.out.println(my_coords2[0] + " " + my_coords2[1]); /* coordinates of escape */
            mazeGeneration(table, my_coords1, my_coords2);
        }else if(side1 == 1){
            side2 = 0;
            my_coords2 = side_to_coordinates(side2,table).split(":");
            System.out.println(my_coords1[0] + " " + my_coords1[1]); /* coordinates of entry */
            System.out.println(my_coords2[0] + " " + my_coords2[1]); /* coordinates of escape */
            mazeGeneration(table, my_coords1, my_coords2);
        }else if(side1 == 2){
            side2 = 3;
            my_coords2 = side_to_coordinates(side2,table).split(":");
            System.out.println(my_coords1[0] + " " + my_coords1[1]); /* coordinates of entry */
            System.out.println(my_coords2[0] + " " + my_coords2[1]); /* coordinates of escape */
            mazeGeneration(table, my_coords1, my_coords2);
        }else if(side1 == 3){
            side2 = 2;
            my_coords2 = side_to_coordinates(side2,table).split(":");
            System.out.println(my_coords1[0] + " " + my_coords1[1]); /* coordinates of entry */
            System.out.println(my_coords2[0] + " " + my_coords2[1]); /* coordinates of escape */
            mazeGeneration(table, my_coords1, my_coords2);
        }




        /* That part display the amazing maze */
        for(int x = 0; x < table.length; x++){
            for (int y = 0; y < table[x].length; y++) {
                if (table[x][y] == "O"){
                    System.out.print(ANSI_RED + table[x][y] + ANSI_RESET + "  ");
                }else{
                    System.out.print(ANSI_GREEN + table[x][y] + ANSI_RESET + "  ");
                }
            }
            System.out.println();
        }
    }

    private static void mazeGeneration(String[][] maze, String[] coord_entrance, String[] coord_exit){
        int magic_random_number = (int) (Math.random() * 4);

        maze[Integer.parseInt(coord_entrance[0])][Integer.parseInt(coord_entrance[1])] = "O";
        maze[Integer.parseInt(coord_exit[0])][Integer.parseInt(coord_exit[1])] = "O";

        int coord_x = 0;
        int coord_y = 0;

        if(coord_entrance[0].equals("0")){
            maze[1][Integer.parseInt(coord_entrance[1])] = "O";
            coord_x = 1;
            coord_y = Integer.parseInt(coord_entrance[1]);
        }
        else if(coord_entrance[0].equals("14")){
            maze[13][Integer.parseInt(coord_entrance[1])] = "O";
            coord_x = 13;
            coord_y = Integer.parseInt(coord_entrance[1]);
        }
        else if(coord_entrance[1].equals("0")){
            maze[Integer.parseInt(coord_entrance[0])][1] = "O";
            coord_x = Integer.parseInt(coord_entrance[0]);
            coord_y = 1;
        }
        else if(coord_entrance[1].equals("14")){
            maze[Integer.parseInt(coord_entrance[0])][13] = "O";
            coord_x = Integer.parseInt(coord_entrance[0]);
            coord_y = 13;
        }
        if(coord_exit[0].equals("0")){
            maze[1][Integer.parseInt(coord_exit[1])] = "O";
        }
        else if(coord_exit[0].equals("14")){
            maze[13][Integer.parseInt(coord_exit[1])] = "O";
        }
        else if(coord_exit[1].equals("0")){
            maze[Integer.parseInt(coord_exit[0])][1] = "O";
        }
        else if(coord_exit[1].equals("14")){
            maze[Integer.parseInt(coord_exit[0])][13] = "O";
        }

        System.out.println(magic_random_number);

        while(maze[coord_x][coord_y] != maze[Integer.parseInt(coord_exit[0])][Integer.parseInt(coord_exit[1])]){
            magic_random_number = (int) (Math.random() * 4);
            if(magic_random_number == 0){
                if(maze[coord_x + 1][coord_y] == maze[14][coord_y]){
                    break;
                }
                else{
                    maze[coord_x + 1][coord_y] = "O";
                }
            }
            else if(magic_random_number == 1){
                if(maze[coord_x - 1][coord_y] == maze[0][coord_y]){
                    break;
                }
                else{
                    maze[coord_x - 1][coord_y] = "O";
                }
            }
            else if(magic_random_number == 2){
                if(maze[coord_x][coord_y + 1] == maze[coord_x][14]){
                    break;
                }
                else{
                    maze[coord_x][coord_y + 1] = "O";
                }
            }
            else{
                if(maze[coord_x][coord_y - 1] == maze[coord_x][0]){
                   break;
                }
                else {
                    maze[coord_x][coord_y - 1] = "O";
                }
            }
        }

    }

    private static String side_to_coordinates (int myside, String[][] mytable){
        /*
        variation of y:
            0 : x= max-range         y= 0 -> max-range    right
            1 : x= 0                y= 0 -> max-range    left

        variation de x:
            2 : x= 0 -> max-range    y= max-range          bottom
            3 : x= 0 -> max-range    y= 0                top
         */
        int x = 0;
        int y = 0;
        if (myside == 0){
            x = mytable.length-1;
            y = (int) (Math.random() * (mytable.length));
        }else if(myside == 1){
            y = (int) (Math.random() * (mytable.length));
        }else if(myside == 2){
            x = (int) (Math.random() * (mytable.length));
            y = mytable.length-1;
        }else if(myside == 3){
            x = (int) (Math.random() * (mytable.length));
        }
        return x + ":" + y;

    }
}

