import java.util.HashMap;
import java.util.Map;

public class TuringMachineSimulation {

    public static void main(String[] args) {
        Map<StateSymbolPair, Transition> transitionFunctions = new HashMap<>();
        transitionFunctions.put(new StateSymbolPair("q0", 'a'), new Transition("q1", 'x', 'R'));
        transitionFunctions.put(new StateSymbolPair("q0", 'y'), new Transition("q3", 'y', 'R'));
        transitionFunctions.put(new StateSymbolPair("q1", 'a'), new Transition("q1", 'a', 'R'));
        transitionFunctions.put(new StateSymbolPair("q1", 'y'), new Transition("q1", 'y', 'R'));
        transitionFunctions.put(new StateSymbolPair("q1", 'b'), new Transition("q2", 'y', 'L'));
        transitionFunctions.put(new StateSymbolPair("q2", 'a'), new Transition("q2", 'a', 'L'));
        transitionFunctions.put(new StateSymbolPair("q2", 'y'), new Transition("q2", 'y', 'L'));
        transitionFunctions.put(new StateSymbolPair("q2", 'x'), new Transition("q0", 'x', 'R'));
        transitionFunctions.put(new StateSymbolPair("q3", 'y'), new Transition("q3", 'y', 'R'));
        transitionFunctions.put(new StateSymbolPair("q3", 'B'), new Transition("q4", 'B', 'L'));

        String initialState = "q0";
        String finalState = "q4";
        char[] tape = {'a', 'a', 'a', 'a', 'b', 'b', 'b', 'b'};

        String result = simulateTuringMachine(transitionFunctions, initialState, finalState, tape);
        System.out.println("Result: " + result);
    }

    public static String simulateTuringMachine(Map<StateSymbolPair, Transition> transitionFunctions,
                                               String initialState, String finalState, char[] tape) {
        String currentState = initialState;
        int currentPosition = 0;

        while (!currentState.equals(finalState)) {
            char currentSymbol = currentPosition < tape.length ? tape[currentPosition] : 'B';

            StateSymbolPair stateSymbolPair = new StateSymbolPair(currentState, currentSymbol);

            if (!transitionFunctions.containsKey(stateSymbolPair)) {
                return "reject";
            }

            Transition transition = transitionFunctions.get(stateSymbolPair);
            currentState = transition.getNewState();
            tape[currentPosition] = transition.getWriteSymbol();

            if (transition.getMoveDirection() == 'R') {
                currentPosition++;
            } else if (transition.getMoveDirection() == 'L') {
                currentPosition--;
            }

            System.out.println("String: " + new String(tape));
            System.out.println("Position: " + currentPosition);
            System.out.println("Read: " + currentSymbol);
            System.out.println("Write: " + transition.getWriteSymbol());
            System.out.println("Move: " + transition.getMoveDirection());
            System.out.println();
        }

        return "accept";
    }

    static class StateSymbolPair {
        private String state;
        private char symbol;

        public StateSymbolPair(String state, char symbol) {
            this.state = state;
            this.symbol = symbol;
        }

        public String getState() {
            return state;
        }

        public char getSymbol() {
            return symbol;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            StateSymbolPair other = (StateSymbolPair) obj;
            return state.equals(other.state) && symbol == other.symbol;
        }

        @Override
        public int hashCode() {
            int result = state.hashCode();
            result = 31 * result + symbol;
            return result;
        }
    }

    static class Transition {
        private String newState;
        private char writeSymbol;
        private char moveDirection;

    public Transition(String newState, char writeSymbol, char moveDirection) {
                this.newState = newState;
                this.writeSymbol = writeSymbol;
                this.moveDirection = moveDirection;
            }

            public String getNewState() {
                return newState;
            }

            public char getWriteSymbol() {
                return writeSymbol;
            }

            public char getMoveDirection() {
                return moveDirection;
            }
        }
    }