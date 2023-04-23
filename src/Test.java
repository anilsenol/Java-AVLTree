import java.io.File;
import java.util.Scanner;

/* Project2: AVL Tree  */
public class Test {
    Song[] songs;
    AVLTree SongsTree = new AVLTree();
    AVLTree IDTree = new AVLTree();
    AVLTree ArtistTree = new AVLTree();

    public static void main(String[] args) {
        Test test = new Test();

        test.getSong();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Select one:\n");
        
        while (true) {
            System.out.println("Menu Items:");
            System.out.println("1. Search ");
            System.out.println("2. Delete");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            if (3 == choice) {
                System.out.println("Exiting.");
                break;
            }
            switch (choice) {
                case 1:
                    test.Menu(scanner);
                    break;
                case 2:
                    test.Menu(scanner);
                    break;
                default:
                    System.out.println("Select nonvalid item.");
                    break;
            }
        }
        scanner.close();
    }

    
    private void Menu(Scanner sc) {
        while (true) {
            System.out.println("Select search type");
            System.out.println("1. by a single word ");
            System.out.println("2. by ID");
            System.out.println("4. Exit");

            int choice = sc.nextInt();
            if (choice == 4) {
                System.out.println("Exiting...");
                break;
            }
            if(choice == 1){
                SearchName(sc);
            }else if(choice == 2){
                SearchID(sc);
            }else
                System.out.println("Nonvalid.");
            
        }
    }

    
    private void SearchName(Scanner scanner) {
        
        
        while (true) {
            System.out.println("Select a search type");
            System.out.println("1. Song Name ");
            System.out.println("2. ID");
            System.out.println("3. Artist");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            if (4 == choice) { 
                break;
            }

            System.out.println("Enter a song name:");
            String song = scanner.next();

            Song rsong = null;
            
            if(choice == 1){
                Node sonucNode = SongsTree.getNodeByKey(song);
                    rsong = (null == sonucNode) ? null : songs[sonucNode.a];
            }else if(choice == 2){
                Node resultID = IDTree.getNodeByKey(Integer.parseInt(song));
                    rsong = (null == resultID) ? null : songs[resultID.a];
            }else if(choice == 3){
                Node resultArtist = ArtistTree.getNodeByKey(song);
                    rsong = (null == resultArtist) ? null : songs[resultArtist.a];
            }else{
                System.out.println("Select invalid item.");
            }
            System.out.println("Search result:");
            if (null != rsong) {
                System.out.println(rsong);
            } else {
                System.out.println("Cannot find any song!");
            }
        }
    }

    
    private void SearchID(Scanner scanner) {
        System.out.println("Type an ID range(minID maxID)");

        try {
            int firstid = scanner.nextInt();
            int lastid = scanner.nextInt();

            for (int id = firstid; id <= lastid; id++) {
                System.out.print(id+ " : ");
                Node n = IDTree.getNodeByKey(id);
                if (n != null) {
                    System.out.println(songs[n.a]);
                } else {
                    System.out.println("None");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    private void Delete(Scanner scanner) {
        System.out.println("Enter ID for deletion");

        try {
            int id = scanner.nextInt();
            System.out.print(id);
            Node n = IDTree.getNodeByKey(id);
            if (n != null) {
                System.out.print(songs[n.a]);
                System.out.print(" will be deleted");

                Song song = songs[n.a];
                IDTree.deleteNode(id);
                ArtistTree.deleteNode(song.getArtist());
                SongsTree.deleteNode(song.getSongName());
            } else {
                System.out.print(" : None");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    private void getSong() {
        Scanner scannerFile = null;
        try {
            File songsfile = new File("/Users/anilsenol/Desktop/songs.txt");
            scannerFile = new Scanner(songsfile);

            int lineNumber = 0;
            while (scannerFile.hasNextLine()) {
                scannerFile.nextLine();
                lineNumber++;
            }
            scannerFile.close();

            songs = new Song[lineNumber];

            scannerFile = new Scanner(songsfile);

            lineNumber = 0;
            while (scannerFile.hasNextLine()) {
                String line = scannerFile.nextLine();
                String[] array = line.split(";");
                try {
                    Song song = new Song(array[0], array[1], Integer.parseInt(array[2]), array[3], array[4]);

                    songs[lineNumber] = song;

                    SongsTree.insert(array[0], lineNumber);
                    IDTree.insert(Integer.parseInt(array[2]), lineNumber);
                    ArtistTree.insert(array[1], lineNumber);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                lineNumber++;
            }
            scannerFile.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
