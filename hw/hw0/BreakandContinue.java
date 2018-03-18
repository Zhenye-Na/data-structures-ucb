/*
continue statement skips the current iteration of the loop, effectively jumping
straight to the increment condition.
*/

public class ContinueDemo {
    public static void main(String[] args) {
        String[] a = {"cat", "dog", "laser horse", "ketchup", "horse", "horbse"};

        for (int i = 0; i < a.length; i += 1) {
            if (a[i].contains("horse")) {
                continue;
            }
            for (int j = 0; j < 3; j += 1) {
                System.out.println(a[i]);
            }
        }
    }
}

/*
break keyword completely terminates the innermost loop when it is called.
*/

public class BreakDemo {
    public static void main(String[] args) {
        String[] a = {"cat", "dog", "laser horse", "ketchup", "horse", "horbse"};

        for (int i = 0; i < a.length; i += 1) {
            for (int j = 0; j < 3; j += 1) {
                System.out.println(a[i]);
                if (a[i].contains("horse")) {
                    break;
                }                
            }
        }
    }
}