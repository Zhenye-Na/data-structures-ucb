public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        while (x < 10) {
            System.out.print(x + " ");
            x = x + 1;
        }
    }
}


/*
    1. Our variable x must be declared before it is used, and it must be given a type!
    2. Our loop definition is contained inside of curly braces {}, and the boolean expression 
       that is tested is contained inside of parentheses.
    3. Our print statement is just System.out.print instead of System.out.println. This means 
       we should not include a newline.
    4. Our print statement adds a number to a space. This makes sure the numbers don't run 
       into each other. Try removing the space to see what happens.
    5. When we run it, our prompt ends up on the same line as the numbers (which you can 
       fix in the following exercise if you'd like).
    6. Java variables' type can never change. eg. int x = 0; x = "horse"  <- Error!
    7. Types are varified before the code even runs.
    8. The compiler ensures that all types are compatible, making it easier for the programmer 
       to debug their code.
    9. Since the code is guaranteed to be free of type errors, users of your compiled programs 
       will never run into type errors. For example, Android apps are written in Java, and are 
       typically distributed only as .class files, i.e. in a compiled format. As a result, such
       applications should never crash due to a type error.
    10. Every variable, parameter, and function has a declared type, making it easier for a 
       programmer to understand and reason about code.
*/