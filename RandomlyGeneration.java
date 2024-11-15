import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;


public class RandomlyGeneration {
    // states Q
    private Set<String> states;
// Input symbols E
    private Set<Character> inputSymbols;
// transition func
    private Map<String, Map<Character, String>> transitionFunction;
// start state q.
    private String startState;
// accept state F
    private Set<String> acceptStates;
// creating random
    private Random random;


//constructor to initialize 
    public RandomlyGeneration(){
        this.states = new HashSet<>();
        this.inputSymbols = new HashSet<>();
        this.transitionFunction = new HashMap();
        this.acceptStates = new HashSet<>();
        this.random = new Random();
    }

//to generate random states
    public void generateRandomStates(int noOfStates){
        Random random = new Random();
        for(int i=0; i<noOfStates; i++){
            // to gen random states like q1 , q2 etc
            String state = "q" + random.nextInt(100);
            states.add(state);
        }
    }

    //for generating random symbols
    public void generateRandomSymbols(int noOfSymbols){
        Random random = new Random();
        for(int i=0; i<noOfSymbols; i++){
            // to gen random input symbols like a , c etc
            char symbol = (char)('a' + random.nextInt(26));
            inputSymbols.add(symbol);
        }
    }

    // Method to set a random start state
    public void setStartState() {
        Random random = new Random();
        // Convert the set of states to an array and pick a random one
        String[] statesArray = states.toArray(new String[0]);
        startState = statesArray[random.nextInt(states.size())]; // Random start state
    }

     // Method to generate the transition function
     public void generateTransitionFunction() {
        Random random = new Random();
        for (String state : states) {
            Map<Character, String> transitions = new HashMap<>();
            for (char symbol : inputSymbols) {
                // Randomly pick the next state from the set of states
                String nextState = (String) states.toArray()[random.nextInt(states.size())];
                transitions.put(symbol, nextState);
            }
            transitionFunction.put(state, transitions);
        }
    }



    // to generate the final states (accept states) randomly
    public void generateFinalStates() {
        Random random = new Random();
        String[] statesArray = states.toArray(new String[0]);
        
        // Generate a random number of final states between 1 and total number of states
        int numFinalStates = random.nextInt(states.size()) + 1;
        
        // Randomly pick final states
        for (int i = 0; i < numFinalStates; i++) {
            String finalState = statesArray[random.nextInt(states.size())];
            acceptStates.add(finalState);
        }
    }


    //  to generate a random input string of random length between 1 and maxLength
    public String generateRandomInputString(int maxLength) {
    Random random = new Random();
    
    // Use the set of input symbols from the automaton (e.g., 'u', 'g' in your case)
    List<Character> inputSymbolsList = new ArrayList<>(inputSymbols);  // Convert the Set to a List for easy access

    // Generate a random string using only the available input symbols
    int length = random.nextInt(maxLength) + 1;  // Random string length between 1 and maxLength
    StringBuilder inputString = new StringBuilder();
    
    // Generate the string
    for (int i = 0; i < length; i++) {
        // Randomly pick one of the input symbols from the available set
        char symbol = inputSymbolsList.get(random.nextInt(inputSymbolsList.size()));
        inputString.append(symbol);
    }

    return inputString.toString();
}
    // now to check strings implementing function

public boolean getResult(String input){
    String currentState = startState;

    for(int i =0; i<input.length(); i++ ){
    char symbol = input.charAt(i);
    if(!transitionFunction.containsKey(currentState) ||
        !transitionFunction.get(currentState).containsKey(symbol)){
        return false;
        }

        currentState = transitionFunction.get(currentState).get(symbol);
    }
    return acceptStates.contains(currentState);

}


    



//to display states
    public void displayStates(){
        System.out.println("Generated States:");
        for (String state:states){
            System.out.println(state);
        }
    }

//to  display symbols
    public void displayInputSymbols(){
        System.out.println("Generated Input symbols are:");
        for (char symbol:inputSymbols){
            System.out.println(symbol);
        }
    }


    // Method to display the transition function
    public void displayTransitionFunction() {
        System.out.println("Transition Function:");
        for (String state : transitionFunction.keySet()) {
            System.out.println("State " + state + ":");
            Map<Character, String> transitions = transitionFunction.get(state);
            for (Character symbol : transitions.keySet()) {
                System.out.println("  On symbol '" + symbol + "' -> " + transitions.get(symbol));
            }
        }
    }


    // Method to display the start state
    public void displayStartState() {
        System.out.println("Start State: " + startState);
    }

    // Method to display the accept states
    public void displayAcceptStates() {
        System.out.println("Accept States:");
        for (String state : acceptStates) {
            System.out.println(state);
        }
    }



    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of states: ");
        int noOfStates = scanner.nextInt();

        //generating random states
        RandomlyGeneration automaton = new RandomlyGeneration();

        System.out.print("Enter the number of input symbols : ");
        automaton.generateRandomStates(noOfStates);
        automaton.displayStates();



        System.out.print("Enter the number of input symbols to generate: ");
        int numSymbols = scanner.nextInt();

         // Generate random input symbols
        automaton.generateRandomSymbols(numSymbols);
        // Display the generated input symbols
        automaton.displayInputSymbols();


        // Set a random start state
        automaton.setStartState();
        automaton.displayStartState();


         // Generate the transition function
         automaton.generateTransitionFunction();
         automaton.displayTransitionFunction();

         // Generate final (accept) states randomly
        automaton.generateFinalStates();
        automaton.displayAcceptStates();



        // Generate a random input string of random length (maximum length = 10 for example)
    String randomInput = automaton.generateRandomInputString(10); // Max length of 10
    
    // Display the generated random input string
    System.out.println("Generated Random Input String: " + randomInput);

    // Process the input string through the automaton and display the result
    boolean result = automaton.getResult(randomInput);
    if (result) {
        System.out.println("Input String is Accepted");
    } else {
        System.out.println("Input String is Rejected");
    }


    Scanner input = new Scanner(System.in);


    // taking input from user and exiting on typing exit
    while(true){
        System.out.print("Enter a string to check or type 'exit' to quit:");
    String in = input.nextLine();

    if(in.equalsIgnoreCase("exit")){
        System.out.println("Exiting the program");
        break;
    }


    if(automaton.getResult(in)){
        System.out.println("Accepted");
        }
        else{
        System.out.println("Rejected");
        }

    }

    input.close();

    // Close the scanner
    scanner.close();


    

    }
    
}
