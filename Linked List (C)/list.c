/**
 * CS 2110 - Spring 2013 - Homework #11
 * Edited by: Brandon Whitehead, Andrew Wilder
 *
 * list.c: Complete the functions!
 */

#include <stdlib.h>
#include <stdio.h>
#include "list.h"

/*Ravish Chawla*/
/* The node struct.  Has a prev pointer, next pointer, and data. */
/* DO NOT DEFINE ANYTHING OTHER THAN WHAT'S GIVEN OR YOU WILL GET A ZERO*/
/* Design consideration only this file should know about nodes */
/* Only this file should be manipulating nodes */
/* DO NOT TOUCH THIS DECLARATION DO NOT PUT IT IN OTHER FILES */
typedef struct lnode
{
  struct lnode* prev; /* Pointer to previous node */
  struct lnode* next; /* Pointer to next node */
  void* data; /* User data */
} node;


/* Do not create any global variables here. Your linked list library should obviously work for multiple linked lists */
// This function is declared as static since you should only be calling this inside this file.
static node* create_node(void* data);

/** create_node
  *
  * Helper function that creates a node by allocating memory for it on the heap.
  * Be sure to set its pointers to NULL.
  *
  * @param data a void pointer to data the user wants to store in the list
  *er@return a node
  */
static node* create_node(void* data)
{

    node *temp = (node *)malloc(sizeof(node));
    temp -> prev = NULL;
    temp -> next = NULL;
    temp -> data = data;


    /// @todo Implement changing the return value!
    return temp;
}

/** create_list
  *
  * Creates a list by allocating memory for it on the heap.
  * Be sure to initialize size to zero and head/tail to NULL.
  *
  * @return an empty linked list
  */
list* create_list(void)
{
	
	list *temp = (list *)malloc(sizeof(list));
	temp -> head = NULL;
	temp -> tail = NULL;
	temp -> size = 0;
	

	/// @todo Implement changing the return value!
    return temp;
}

/** push_front
  *
  * Adds the data to the front of the linked list
  *
  * @param llist a pointer to the list.
  * @param data pointer to data the user wants to store in the list.
  */
void push_front(list* llist, void* data)
{
	node *temp = create_node(data);
			
	if(llist -> head == NULL)
	{
		llist -> head = temp;
		llist -> tail = temp;
		
	}
	else{
		temp -> next = llist -> head;
		llist -> head -> prev = temp;
		llist -> head = temp;
	}

	
	llist -> size++;
		

    /// @todo Implement
}

/** push_back
  *
  * Adds the data to the back/end of the linked list
  *
  * @param llist a pointer to the list.
  * @param data pointer to data the user wants to store in the list.
  */
void push_back(list* llist, void* data)
{
	
	if(llist -> tail == NULL)
		push_front(llist, data);
	
	else{
		node *temp = create_node(data);
		llist -> tail -> next = temp;
		temp -> prev = llist -> tail;
		llist -> tail = temp;
		llist -> size++;	

	}

	

/// @todo Implement
}

/** front
  *
  * Gets the data at the front of the linked list
  * If the list is empty return NULL.
  *
  * @param llist a pointer to the list
  * @return The data at the first node in the linked list or NULL.
  */
void* front(list* llist)
{
	if(llist -> head == NULL)
		return NULL;
	else
		return llist -> head -> data;
	
    /// @todo Implement changing the return value!
    /// @note you are returning the HEAD's DATA not the head node. Remember the user should never deal with the linked list nodes.
}

/** back
  *
  * Gets the data at the "end" of the linked list
  * If the list is empty return NULL.
  *
  * @param llist a pointer to the list
  * @return The data at the last node in the linked list or NULL.
  */
void* back(list* llist)
{
	if(llist -> tail == NULL)
		return NULL;
	else
		return llist -> tail -> data;
	/// @todo Implement changing the return value!
    
}

/** remove_front
  *
  * Removes the node at the front of the linked list
  *
  * @warning Note the data the node is pointing to is also freed. If you have any pointers to this node's data it will be freed!
  *
  * @param llist a pointer to the list.
  * @param free_func pointer to a function that is responsible for freeing the node's data.
  * @return -1 if the remove failed (which is only there are no elements) 0 if the remove succeeded.
  */
int remove_front(list* llist, list_op free_func)
{
	if(llist -> tail == NULL)
				return -1;
	
	node *temp = llist -> head;
	llist -> head = temp -> next;

	if(llist -> head == NULL)
		llist -> tail = NULL;

	temp -> prev = NULL;
	
	free_func(temp -> data);
	free(temp);

	llist -> size--;
    /// @todo Implement
    /// @note remember to also free the node itself
    /// @note free_func is a function that is responsible for freeing the node's data only.
    return 0;
}

/** remove_back
  *
  * Removes the node at the back of the linked list
  *
  * @warning Note the data the node is pointing to is also freed. If you have any pointers to this node's data it will be freed!
  *
  * @param llist a pointer to the list.
  * @param free_func pointer to a function that is responsible for freeing the node's data.
  * @return -1 if the remove failed 0 if the remove succeeded.
  */
int remove_back(list* llist, list_op free_func)
{
    /// @todo Implement
   
	if(llist -> head == NULL)
		return -1;

	node *temp = llist -> tail;
	if(temp -> prev != NULL)
			temp -> prev -> next = NULL;
	llist -> tail = temp -> prev;
	
	if(llist -> tail == NULL)
		llist -> head = NULL;

	free_func(temp -> data);
	free(temp);
		
	llist -> size--;
	
	/// @note Remember to also free the node itself
    /// @note free_func is a function that is responsible for freeing the node's data only.
    return 0;
}

/** copy_list
  *
  * Create a new list structure, new nodes, and new copies of the data by using
  * the copy function. Its implementation for any test structure must copy
  * EVERYTHING!
  *
  * @param llist A pointer to the linked list to make a copy of
  * @param copy_func A function pointer to a function that makes a copy of the
  *        data that's being used in this linked list, allocating space for
  *        every part of that data on the heap. This is some function you must
  *        write yourself for testing, tailored specifically to whatever context
  *        you're using the linked list for in your test.
  * @return The linked list created by copying the old one
  */
list* copy_list(list* llist, list_cpy copy_func)
{
	list *temp = create_list();
	node *tCurr = llist -> head;
	while(tCurr != NULL)
	{
		push_back(temp, copy_func(tCurr -> data));
		
		tCurr = tCurr -> next;
	}


	/// @todo implement
	return temp;
}

/** size
  *
  * Gets the size of the linked list
  *
  * @param llist a pointer to the list
  * @return The size of the linked list
  */
int size(list* llist)
{
    ///@note simply return the size of the linked list.  Its that easy!
   return llist -> size;
}

/** remove_if
  *
  * Removes all nodes whose data when passed into the predicate function returns true
  *
  * @param llist a pointer to the list
  * @param pred_func a pointer to a function that when it returns true it will remove the element from the list and do nothing otherwise @see list_pred.
  * @param free_func a pointer to a function that is responsible for freeing the node's data
  * @return the number of nodes that were removed.
  */
int remove_if(list* llist,list_pred pred_func, list_op free_func)
{
	
	node *curr = llist -> head;
	int retVal = 0;

	while (curr != NULL)
	{
		node *temp = curr -> next;
		if(pred_func(curr -> data))
		{
			

			if(curr -> prev == NULL)
				llist -> head = curr -> next;
			else
				curr -> prev -> next = curr -> next;

			if(curr -> next == NULL)
				llist -> tail = curr -> prev;
			else
				curr -> next -> prev = curr -> prev;

			free_func(curr -> data);
			free(curr);
			retVal++;

			
		}
		curr = temp;
	}


    /// @todo Implement changing the return value!
    /// @note remember to also free all nodes you remove.
    /// @note be sure to call pred_func on the NODES DATA to check if the node needs to be removed.
    /// @note free_func is a function that is responsible for freeing the node's data only.
	llist -> size -= retVal;
	return retVal;
}

/** is_empty
  *
  * Checks to see if the list is empty.
  *
  * @param llist a pointer to the list
  * @return 1 if the list is indeed empty 0 otherwise.
  */
int is_empty(list* llist)
{
    ///@note an empty list by the way we want you to implement it has a size of zero and head points to NULL.
	
	
	//int con2 = llist -> head == NULL;

	//printf("size? :%d\n", llist -> size);
	//printf("head Null? :%d\n", con2);
  	return ((llist -> size == 0) && (llist -> head == NULL));

}

/** empty_list
  *
  * Empties the list after this is called the list should be empty.
  *
  * @param llist a pointer to a linked list.
  * @param free_func function used to free the node's data.
  */
void empty_list(list* llist, list_op free_func)
{

	while(!is_empty(llist))
		remove_front(llist, free_func);
	

    /// @todo Implement
    /// @note Free all of the nodes not the linked list itself.
    /// @note do not free llist.
}

/** traverse
  *
  * Traverses the linked list calling a function on each node's data.
  *
  * @param llist a pointer to a linked list.
  * @param do_func a function that does something to each node's data.
  */
void traverse(list* llist, list_op do_func)
{
	node *curr = llist -> head;

	while(curr != NULL)
	{
		do_func(curr -> data);

		curr = curr -> next;


	}

    /// @todo Implement
}
