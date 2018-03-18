Populate that folder with the Java libraries we need for this class.

1. First, open up a terminal window and cd into your three-letter repository.

2. Run

   git submodule update --init

   You should get output like this:

   Submodule 'library-sp18' (https://github.com/Berkeley-CS61B/library-sp18.git)
   registered for path 'library-sp18'
   Cloning into '/Users/vivian/class/cs61b/viv/library-sp18'...
   Submodule path 'library-sp18': checked out
   '2bd75038edcf1ac7bacdb9e85fc08853153bd4e8'

3. Check

   $ ls library-sp18/
   javalib/