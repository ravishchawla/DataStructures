//SLL
void insert_after(Node node, Node newNode)
{
	newNode.next = node.next;
	node.next = newNode;
}

void insertFront(List list, Node newNode)
{
	newNode.next = list.head;
	list.head = newNode;
}

void removeAfter(Node node)
{
	Node temp = node.next;
	node.next = node.next.next;
	free(temp);
}

void removeBeginning(List list)
{
	Node temp = list.head;
	list.head = list.head.next;
	free(temp);
}

//CSLL
void traverse(node temp)
{
	if(temp != NULL)
	{
		Node curr = temp;

	do{
		curr = curr.next;
	}
	while(curr != temp);
}

void insertAfter(Node node, Node newNode)
{
	if(node == NULL)
		newNode.next = newNode;
	else
	{
		newNode.next = node.next;
		node.next = newNode;
	}
}

void insertBack(Node tail, Node newNoe)
{
	insertAfter(tail, newNode);
	tail = newNode;
}

void insertFront(Node tail, Node newNode)
{
	insertAfter(tail, newNode);
	if(tail == NULL)
	{
		tail = newNode;
	}
}

//DLL
void insertAfter(List list, Node node, Node newNode)
{
	newNode.prev = node;
	newNode.next = node.next;
	if(node.next == NULL)
	{
		list.lastNode = newNode;
	}
	else
		node.next.prev = newNode;

	node.next = newNode;
}

//CDLL
void traverse(Node temp)
{
	Node node = temp;
	do
	{
		node = node.next;
	}
	while(node != temp);
}

void insertAfter(Node node, Node newNode)
{
	newNode.next = node.next;
	newNode.prev = node;
	node.next.prev = newNode;
	node.next = newNode;
}

void remove(List list, Node node)
{
	if (node.next == node)
		list.lastNode = null;
	else
	{
		node.next.prev = node.prev;
		node.prev.next = node.next;
		if(node == list.lastNode)
			list.lastNode = node.prev;
	}
	free node;
}

