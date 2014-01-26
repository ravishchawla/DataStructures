#include "list.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

/*Ravish Chawla*/
/* Here we are going to write some functions to support a linked list that stores Students (first name, last name)  */
/* Here is the data the user in this case us will be storing into our linked list */
/* Write more test cases! */
typedef struct Student
{
    const char* first_name;
    const char* last_name;
    int *age; // malloc'd. Because why not?
} Student;

/* Example functions given to you. Understand why these are written this way As you will be writing your own functions to test this! */
Student* create_student(const char* first_name, const char* last_name, int age)
{
    Student* s = (Student*) malloc(sizeof(Student));
    s->first_name = first_name;
    s->last_name = last_name;
    s->age = (int*) malloc(sizeof(int));
    *(s->age) = age;
    return s;
}

void* copy_student(const void* data)
{
    Student *s = (Student*) data;
    return create_student(s->first_name, s->last_name, *(s->age));
}

/*A method that returns a Student with age squared. 
*/
void* copy_student0(const void* data)
{
	Student *s = (Student*) data;
	int age = *(s->age);
	return create_student(s->first_name, s->last_name, age*age);
}

void print_student(void* data)
{

    Student *s = (Student*) data;
    printf("%s %s, %d\n", s->first_name, s->last_name, *(s->age));
	
}

/*A simplified print_student method that 
	only prints the ages. 
*/
void print_student_simple(void* data)
{
		if(data == NULL)
		printf("null \n");
	else{

	Student *s = (Student*)data;
	printf("%d ", *(s -> age));
	}
}

/* NOTE: The reason the names aren't freed like the data itself is because they
 * are const char*, meaning they are located in const memory, instead of the
 * heap. You can only free data that is allocated on the heap! */
void free_student(void* data)
{
    Student *s = (Student*) data;

    // free any malloc'd pointers contained in the Student struct
    free(s->age);

    // free the Student structure.
    free(s);
}

/*	A function that frees data, 
	and also deals with nulls. 
*/

void free_student_nulls(void *data)
{

	if(data == NULL)
		return;
	free_student(data);

	


}
/*
	A method that returns true if 
	age of argument is 10. 
*/
int age_is_ten(const void* data)
{
	Student *s = (Student*) data;
	int age = (data == NULL ? 0 :*(s -> age));
	
	return (age == 10);

}
	
/* A function that returns true always. 
*/

int trueVal(const void* data)
{
	return 1;
}
	
/*Two arrays with random numbers. The second
is a shuffled version of the first. Since they 
have very random values, moving through the arrays
to remove and delete allows some random-value testing
*/
int numeros[] = {5,2,7,3,9,1,4,2,11,7,5,1,6,8,3};
int _numeros[] = {11,8,4,3,2,6,7,2,1,9,5,5,1,7,3};
int i = 0;

/* A boolean function that compares the age of the 
data with the next value in _numeros at position i. 
*/
int randomizer(const void* data)
{
	Student *s = (Student*) data;
	int val = 0;
	if(i < sizeof(_numeros)/sizeof(_numeros[0]))
	{
	
	

		val = (*(s -> age) == _numeros[i]);
	}
	
	return val;

}
	
/* This main function does a little testing
   Like all good CS Majors you should test
   your code here. There is no substitute for testing
   and you should be sure to test for all edge cases
   e.g., calling remove_front on an empty list.
*/
int main(void)
{
	/* Now to make use of all of this stuff */
	list* llist = create_list();

  	/* What does an empty list contain?  Lets use our handy traversal function */
  	printf("TEST CASE 1\nAn Empty list should print nothing here:\n");
  	traverse(llist, print_student);
	printf("\n");

 	/* Lets add a student and then print */
 	push_front(llist, create_student("Mahmoud", "Joudeh", 20));
 	printf("TEST CASE 2\nA List with one student should print that student:\n");
	
	traverse(llist, print_student);
 	printf("\n");

 	/* Lets remove that student and then print */
 	remove_front(llist, free_student);
 	printf("TEST CASE 3\nAnother Empty list should print nothing here:\n");
 	
	traverse(llist, print_student);
 	printf("\n");

 	/* Lets add two elements and then print */
 	push_front(llist, create_student("Alex", "Ikonomidis", 19));
 	push_front(llist, create_student("Andrew", "Kim", 21));
 	printf("TEST CASE 4\nA List with two students should print those two students:\n");
 	traverse(llist, print_student);
 	printf("\n");

	/* Lets copy this list */
	list* llist2 = copy_list(llist, copy_student);
	
	printf("TEST CASE 5\nA copied list should print out the same two students:\n");
 	traverse(llist, print_student);
 	printf("\n");

  	/* Lets kill the list */
  	empty_list(llist, free_student);
 	printf("TEST CASE 6\nAfter freeing all nodes the list should be empty:\n");
 	traverse(llist, print_student);
	printf("\n");
	empty_list(llist2, free_student);
	free(llist);
	free(llist2);
 	/* YOU ARE REQUIRED TO MAKE MORE TEST CASES THAN THE ONES PROVIDED HERE */
 	/* You will get points off if you do not you should at least test each function here */

	/*Sample Tests*/
	llist = create_list();
	printf("\n4 Elements added\n ");
	push_front(llist, create_student("Abra", "Cadabra",14));
	push_front(llist, create_student("bing", "google", 10));
	push_front(llist, create_student("costa", "rica", 10));
	push_front(llist, create_student("Delta", " ", 17));
	//push_front(llist, NULL);
	traverse(llist, print_student);
	printf("\n");
		
	
	printf("Removed %d elements of age 10\n", remove_if(llist, age_is_ten, free_student));
	traverse(llist, print_student);
	remove_front(llist, free_student);
	list* list2 = copy_list(llist, copy_student);
	remove_front(llist, free_student);
	remove_back(list2, free_student);
	printf("\nEmptying list \n");
	traverse(llist, print_student_simple);
	
	i = 0;
	printf("\n500 elements being added\n");
	while(i++ < 500)
	{
			
			push_front(llist, create_student("sdflj", "sdlfs", rand()%10));

	}

	printf("\nis Empty? %d\n", is_empty(llist));
	printf("\n");
	//traverse(llist, print_student);
	
	
	
	i = 0;
	
	printf("\nRemoving 500 elements until empty\n");
	while(!is_empty(llist)){

		remove_front(llist, free_student);
	
		//practice(llist);
	}
	printf("\nis Empty? %d\n", is_empty(llist));
	
	empty_list(llist, free_student);

	remove_if(llist, trueVal, free_student);
	
	traverse(llist, print_student);	
	
	printf("\n");
	printf("\nEmptiness\n");
	remove_back(llist, free_student);
	
	traverse(llist, print_student);
	
	traverse(list2, print_student);
	
	
 	/* Testing over clean up*/
	empty_list(llist, free_student);
	empty_list(list2, free_student);
 	free(llist);
	free(list2);
	
	printf("\nAdding random numbers to the list\n");
	llist = create_list();
	i = 0;
	while (i < sizeof(numeros)/sizeof(numeros[0]))
		push_back(llist, create_student("a", "ab", numeros[i++]));

	traverse(llist, print_student_simple);
	printf("\n");

	printf("\nRemoving numbers in a random order\n");
	i = 0;
	while( i < sizeof(_numeros)/sizeof(_numeros[0]))
	{
		int total = remove_if(llist, randomizer, free_student);
		printf("size is %d after %d removes of %d: ", size(llist),  total, _numeros[i]);
		traverse(llist, print_student_simple);
		printf("\n");
		i++;
	}
	printf("\nEmpty size: \n");
	printf("list of size %d: ", size(llist));traverse(llist, print_student_simple);

	printf("\nTesting push_back and remove_if\n");
	push_back(llist, create_student("a", "b", 9));
	traverse(llist, print_student_simple); printf("\n");
	remove_if(llist, age_is_ten, free_student);
	traverse(llist, print_student_simple); printf("\n");
	empty_list(llist, free_student);
	traverse(llist, print_student_simple); printf("\n");
	printf("\nTesting push_front and remove_if\n");
	push_front(llist, create_student("a", "b", 9));
	traverse(llist, print_student_simple); printf("\n");
	remove_if(llist, age_is_ten, free_student);
	traverse(llist, print_student_simple); printf("\n");
	empty_list(llist, free_student);
	traverse(llist, print_student_simple); printf("\n");
	
	printf("\nTesting push_back and remove_if\n");
	push_back(llist, create_student("a", "b", 10));
	traverse(llist, print_student_simple); printf("\n");
	remove_if(llist, age_is_ten, free_student);
	traverse(llist, print_student_simple); printf("\n");
		
	printf("\nTesting push_front and remove_if\n");
	push_front(llist, create_student("a", "b", 10));
	traverse(llist, print_student_simple); printf("\n");
	remove_if(llist, age_is_ten, free_student);
	traverse(llist, print_student_simple); printf("\n");
	
	
	printf("\nTesting push_back and remove_front\n");
	push_back(llist, create_student("a", "b", 9));
	traverse(llist, print_student_simple); printf("\n");
	remove_front(llist, free_student);
	traverse(llist, print_student_simple); printf("\n");
	
	printf("\nTesting push_back and remove_back\n");
	push_back(llist, create_student("a", "b", 9));
	traverse(llist, print_student_simple); printf("\n");
	remove_back(llist, free_student);
	traverse(llist, print_student_simple); printf("\n");

	printf("\nTesting push_front and remove_front\n");
	push_front(llist, create_student("a", "b", 9));
	traverse(llist, print_student_simple); printf("\n");
	remove_front(llist, free_student);
	traverse(llist, print_student_simple); printf("\n");

	printf("\nTesting push_front and remove_back\n");
	push_front(llist, create_student("a", "b", 9));
	traverse(llist, print_student_simple); printf("\n");
	remove_back(llist, free_student);
	traverse(llist, print_student_simple); printf("\n");
	
	printf("\nEVERYTHING WORKS!!!\n");
	
	i = 0;
	printf("\n Testing return value of remove_if on empty list\n");
	while(i++ < 6)
		printf("%d ",remove_if(llist, trueVal, free_student));

	printf("\n Testing return value of remove_front on empty list\n");
	i = 0;
	while(i++ < 6)
		printf("%d ", remove_front(llist, free_student));

	printf("\n Testing return value of remove_back on empty list\n");
	i = 0;
	while(i++ < 6)
		printf("%d ", remove_back(llist, free_student));

	printf("\n Testing return value of remove_if on filled list\n");
	i = 0;
	while(i++ < 6)
		push_back(llist, create_student("a", "b", 3));
	i = 0;
	while(i++ < 6)
		printf("%d ",remove_if(llist, trueVal, free_student));
	
	printf("\n Testing return value of remove_front on filled list\n");
	i = 0;
	while(i++ < 6)
		push_back(llist, create_student("a", "b", 3));
	i = 0;
	while(i++ < 6)
		printf("%d ",remove_front(llist,free_student));

	printf("\n Testing return value of remove_back on filled list\n");
	i = 0;
	while(i++ < 6)
		push_back(llist, create_student("a", "b", 3));
	i = 0;
	while(i++ < 6)
		printf("%d ",remove_back(llist,free_student));
	
	printf("\nTesting size: ");
	i = 0;
	while(i++ < 6)
		push_back(llist, create_student("a", "b", 3));
	
	printf("\nsize %d: ", size(llist));
	printf("\nEmptying it: ");
	empty_list(llist, free_student);
	printf("\tsize %d: ", size(llist));

	i = 0;
	while(i++ < 6)
		push_back(llist, create_student("a", "b", numeros[i]));
	printf("\nTesting copy method, values getting squared\n");
	printf("\n");
	list2 = copy_list(llist, copy_student0);
	traverse(llist, print_student_simple);
	printf("\n");
	traverse(list2, print_student_simple);
	printf("\n");

	printf("\nTesting front and back methods:\n");
	printf("Front: ");print_student(front(llist));
	printf("\nBack: ");print_student(back(llist));
	printf("\n_Front: ");print_student(front(list2));
	printf("\n_Back: ");print_student(back(list2));

	printf("\nTesting remove_front and remove_back together\n");
	i = 0;
	while (i++ < 6)
	{
		remove_back(llist, free_student);
		printf("\n");
		traverse(llist, print_student_simple);
		remove_front(list2, free_student);
		printf("\n");
		traverse(list2, print_student_simple);
	}

	printf("\nEmptying the list\n");
	while(i++ < 9)
		empty_list(llist, free_student);

	printf("\nAdding a null element\n");
	push_back(llist, NULL);
	traverse(llist, print_student_simple);

	list *list3 = create_list();
	empty_list(list3, free_student_nulls);
	
	print_student_simple(front(list3));
	traverse(list3, print_student_simple);

	empty_list(llist, free_student_nulls);
	empty_list(list2, free_student);
	push_back(llist, create_student("one", "two", 5));
	push_back(list2, create_student("one", "two", 10));
	
	printf("\nTesting front and back methods with one element\n");
	printf("Front: ");print_student_simple(front(llist));
	printf("\nBack: ");print_student_simple(back(llist));
	printf("\n_Front: ");print_student_simple(front(list2));
	printf("\n_Back: ");print_student_simple(back(list2));
	
	empty_list(llist, free_student_nulls);
	empty_list(list2, free_student_nulls);
	printf("\nTesting front and back methods with empty list\n");
	printf("Front: ");print_student_simple(front(llist));
	printf("\nBack: ");print_student_simple(back(llist));
	printf("\n_Front: ");print_student_simple(front(list2));
	printf("\n_Back: ");print_student_simple(back(list2));
	printf("\nAll Empty List : \"");
	traverse(llist, print_student_simple);
	traverse(list2, print_student_simple);
	printf("\"\n");
	empty_list(list3, free_student);
	printf("\nYAY\n");
	free(llist);
	free(list2);
	
	free(list3);
	//cd /mnt/hgfs/B/Current\ Projects/HW\ 11\ \(2110\)/
  	return 0;
}
