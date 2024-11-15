import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Automation{
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

//constructor to initialize 
    public Automation(){
        this.states = new HashSet<>();
        this.inputSymbols = new HashSet<>();
        this.transitionFunction = new HashMap();
        this.acceptStates = new HashSet<>();
    }

// to add states
    public void addState(String state){
        states.add(state);
    }

// now to add input symbols

    public void addInputSymbol(char symbol){
        inputSymbols.add(symbol);
    }

// now setting the start state
    public void addStartState(String state){
        this.startState = state;
    }


// now implementing accept states
public void addAcceptState(String state){
    acceptStates.add(state);
}


// now adding transition function here
public void addTransition(String currState, char symbol, String nextState){
     transitionFunction
        .computeIfAbsent(currState, k -> new HashMap<>()) // If no map exists for currentState, create a new map
        .put(symbol, nextState);
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

public static void main(String[] args) {
    Automation automaton = new Automation();
//adding states
    automaton.addState("q0");
    automaton.addState("q1");
    automaton.addState("q2");
    automaton.addState("q3");
    automaton.addState("q4");
    automaton.addState("d0");
//adding input symbols
    automaton.addInputSymbol('a');
    automaton.addInputSymbol('b');
//adding start state
    automaton.addStartState("q0");
// entering accepting states
    automaton.addAcceptState("q2");
    automaton.addAcceptState("q3");
    automaton.addAcceptState("q4");
// entering transition states
    automaton.addTransition("q0", 'a', "q1");
    automaton.addTransition("q0", 'b', "q3");
    automaton.addTransition("q1", 'a', "q2");
    automaton.addTransition("q1", 'b', "d0");
    automaton.addTransition("q2", 'a', "q1");
    automaton.addTransition("q2", 'b', "d0");
    automaton.addTransition("q3", 'a', "d0");
    automaton.addTransition("q3", 'b', "q4");
    automaton.addTransition("q4", 'a', "d0");
    automaton.addTransition("q4", 'b', "d0");
    automaton.addTransition("d0", 'a', "d0");
    automaton.addTransition("d0", 'b', "d0");


    System.out.println("We have added a default automaton by values which was given in assignment you can change values and run it to any automaton.\n");
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

    

}






}