// include relevant packages to ensure functionality throughout the program

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Source to enable DC code to initially run: https://learnonline.gmit.ie/mod/forum/discuss.php?d=1496
#include <errno.h>
size_t getdelim(char **linep, size_t *n, int delim, FILE *fp){
    int ch;
    size_t i = 0;
    if(!linep || !n || !fp){
        errno = EINVAL;
        return -1;
    }
    if(*linep == NULL){
        if(NULL==(*linep = malloc(*n=128))){
            *n = 0;
            errno = ENOMEM;
            return -1;
        }
    }
    while((ch = fgetc(fp)) != EOF){
        if(i + 1 >= *n){
            char *temp = realloc(*linep, *n + 128);
            if(!temp){
                errno = ENOMEM;
                return -1;
            }
            *n += 128;
            *linep = temp;
        }
        (*linep)[i++] = ch;
        if(ch == delim)
            break;
    }
    (*linep)[i] = '\0';
    return !i && ch == EOF ? -1 : i;
}

size_t getline(char **linep, size_t *n, FILE *fp){
    return getdelim(linep, n, '\n', fp);
}

//Establish structures to use further on store various basic information related to aspects of the shop
struct Product {
	char* name;
	double price;
};

struct ProductStock {
	struct Product product;
	int quantity;
};

struct Shop {
	double cash;
	struct ProductStock stock[20];
	int index;
};

struct Customer {
	char* name;
	double budget;
	struct ProductStock shoppingList[10];
	int index;
};
// I ended up setting up a second struct to store information relating to a live ("L") customer
struct LCustomer {
	char* Lname;
	double Lbudget;
	struct ProductStock shoppingList[10];
	int index;
};
// from here, these are more functional structs to  read in a .csv file or receive user input and "do something" with that information.
//In this case, to read the .csv file of the customers order
//this will process fine

struct Customer createCustomer()
{
    FILE * fp;
    char * line = NULL;
    size_t len = 0;
    size_t read;

//read in .csv file
    fp = fopen("Customer.csv", "r");
    if (fp == NULL)
        exit(EXIT_FAILURE);

//read first line and extract customer name and budget and assign to "Customer" struct
    getline(&line, &len, fp);
    char *n = strtok(line, ",");
    char *name = malloc(sizeof(char) * 50);
		strcpy(name, n);
    char *b = strtok(NULL, ",");
    double budget = atof(b);
    struct Customer customer = { name, budget };


//print header for next section
    printf("*** CUSTOMER SHOPPING LIST *** \n");
    printf("\n");
    printf("-- PRODUCT -- QUANTITY -- \n");

//read in the rest of the lines using a while loop to capture each one by one
    while ((read = getline(&line, &len, fp)) != -1) {
		char *n = strtok(line, ",");
		char *q = strtok(NULL, ",");
		int quantity = atoi(q);
		char *name = malloc(sizeof(char) * 50);
		strcpy(name, n);
		struct Product product = { name };
		struct ProductStock shoppingList = { product, quantity };
        customer.shoppingList[customer.index++] = shoppingList;
        int x = (-(strlen(name)) + 16);
        //char* y1 = ftoa(quantity, y1, 1); //potentially better way to present information
        //int y = (-(strlen(y1)) + 6);
        printf("%s  %*d \n", name, x, quantity);

    }
    //activate stuct where the print command and formating instructions are stored
    printCustomer(customer);

	return customer;
}

//I did try to "feed in" the .csv file as an input to createCustomer but I could not get it to run so separate
//structs have been set up for each customer, not optimal but functional.
//This struct is identical to createCustomer with the exception of the .csv route
//this should not process as the customer does not have enough money

struct Customer createCustomer2()
{
    FILE * fp;
    char * line = NULL;
    size_t len = 0;
    size_t read;

//read in .csv file
    fp = fopen("Customer2.csv", "r");
    if (fp == NULL)
        exit(EXIT_FAILURE);

//read first line and extract customer name and budget and assign to "Customer" struct
    getline(&line, &len, fp);
    char *n = strtok(line, ",");
    char *name = malloc(sizeof(char) * 50);
		strcpy(name, n);
    char *b = strtok(NULL, ",");
    double budget = atof(b);
    struct Customer customer = { name, budget };


//print header for next section
    printf("*** CUSTOMER SHOPPING LIST *** \n");
    printf("\n");
    printf("-- PRODUCT -- QUANTITY -- \n");
//read in the rest of the lines using a while loop to capture each one by one
    while ((read = getline(&line, &len, fp)) != -1) {
		char *n = strtok(line, ",");
		char *q = strtok(NULL, ",");
		int quantity = atoi(q);
		char *name = malloc(sizeof(char) * 50);
		strcpy(name, n);
		struct Product product = { name };
		struct ProductStock shoppingList = { product, quantity };
        customer.shoppingList[customer.index++] = shoppingList;
        int x = (-(strlen(name)) + 16);
        printf("%s  %*d \n", name, x, quantity);

    }
    //activate stuct where the print command and formating instructions are stored
    printCustomer(customer);

	return customer;
}
//I did try to "feed in" the .csv file as an input to createCustomer but I could not get it to run so separate
//structs have been set up for each customer, not optimal but functional.
//This struct is identical to createCustomer with the exception of the .csv route
//this should trigger a warning as there is not enough stock to fulfill the order

struct Customer createCustomer3()
{
    FILE * fp;
    char * line = NULL;
    size_t len = 0;
    size_t read;

//read in .csv file
    fp = fopen("Customer3.csv", "r");
    if (fp == NULL)
        exit(EXIT_FAILURE);

//read first line and extract customer name and budget and assign to "Customer" struct
    getline(&line, &len, fp);
    char *n = strtok(line, ",");
    char *name = malloc(sizeof(char) * 50);
		strcpy(name, n);
    char *b = strtok(NULL, ",");
    double budget = atof(b);
    struct Customer customer = { name, budget };


//print header for next section
    printf("*** CUSTOMER SHOPPING LIST *** \n");
    printf("\n");
    printf("-- PRODUCT -- QUANTITY -- \n");
//read in the rest of the lines using a while loop to capture each one by one
    while ((read = getline(&line, &len, fp)) != -1) {
		char *n = strtok(line, ",");
		char *q = strtok(NULL, ",");
		int quantity = atoi(q);
		char *name = malloc(sizeof(char) * 50);
		strcpy(name, n);
		struct Product product = { name };
		struct ProductStock shoppingList = { product, quantity };
        customer.shoppingList[customer.index++] = shoppingList;
        int x = (-(strlen(name)) + 16);
        printf("%s  %*d \n", name, x, quantity);

    }
    //activate stuct where the print command and formating instructions are stored
    printCustomer(customer);

	return customer;
}

//this struct receives in Live Customer information from the user using the scanf() functionality.

struct LCustomer LiveCustomer()
    {
//create strings to use below
    char* Livename[20];
    char* Livebudget[20];
    int LproductInput;
    char* Lproduct;
    int Lquantity;
    int decYN;

//request user input & convert budget to double
    printf("Please enter your name:  ");
    scanf("%s\0" , Livename);
    printf("What is your budget?  ");
    scanf("%s\0", Livebudget);
    double Livebudget2 = atof(Livebudget);

//add both the name and budget input to the Lcustomer struct (Live Customer)
    struct LCustomer Lc = {Livename, Livebudget2};
    printLCustomer(Lc);

//pose questions to the user and save the input in the established string objects
   printf("\n");

//use predefined options to limit user error
   printf("*Select your purchase*\n");
   printf("1) Coke Can\n");
   printf("2) Bread\n");
   printf("3) Spagetti\n");
   printf("4) Tomato Sauce\n");
   printf("5) Bin Bags\n");
   scanf("%d", &LproductInput);//live mode: https://www.tutorialspoint.com/c_standard_library/c_function_scanf.htm
    if (LproductInput == 1){ Lproduct = "CokeCan";}
    else if (LproductInput == 2){ Lproduct = "Bread";}
    else if (LproductInput == 3){ Lproduct = "Spagetti";}
    else if (LproductInput == 4){ Lproduct = "TomatoSauce";}
    else if (LproductInput == 5){ Lproduct = "BinBags";}

    printf("How Many?  ");
    scanf("%d", &Lquantity);

//add both the name of the product and quantity to the shoppingList struct

    printf("\n");
    printf("Adding to shoppingList....\n");
    printf("\n");
    printf("-- PRODUCT -- PRICE -- QUANTITY --\n");
    struct Product product = { Lproduct };
    struct ProductStock shoppingList = { product, Lquantity }; //add the products to the shoppingList
    printProduct(product);
	printf("  %d        \n", Lquantity);
    printf("Added to shoppingList.\n");


return Lc;
}

//struct to print Live customer details in a defined format
void printLCustomer(struct LCustomer c)
{
	printf("\n");
    printf("CUSTOMER NAME: %s \nCUSTOMER BUDGET: %.2f\n", c.Lname, c.Lbudget);
	printf("===============================\n");

	}



//struct to print product details in a defined format
void printProduct(struct Product p)
{
    int x = (-(strlen(p.name)) + 16); //source: https://www.programiz.com/c-programming/library-function/string.h/strlen
    //printf("%d", x);
	printf(" %s %*.2f      ", p.name,x, p.price);

}


//struct to print customer details in a defined format
void printCustomer(struct Customer c)
{
	printf("\n");
    printf("CUSTOMER NAME: %s \nCUSTOMER BUDGET: %.2f\n", c.name, c.budget);
	printf("===============================\n");

//    double sum = 0;

//    for(int i = 0; i < 6; i++)
//	{
//	    double totalProductPrice = c.shoppingList[i].quantity * c.shoppingList[i].product.price;
//        sum += totalProductPrice;
//
//       }
//    printf("%s's order comes to total of: %d \n", c.name, sum);
	}



//struct to set up shop environment, model shop stock and add date to productStock struct
struct Shop createAndStockShop()
{
//	struct Shop shop = { 200 };
    FILE * fp;
    char * line = NULL;
    size_t len = 0;
    size_t read;

    fp = fopen("stock.csv", "r");
    if (fp == NULL)
        exit(EXIT_FAILURE);

    getline(&line, &len, fp);
 	double cash = atof(line);
    struct Shop shop = {cash};


    while ((read = getline(&line, &len, fp)) != -1) {
		char *n = strtok(line, ",");
		char *p = strtok(NULL, ",");
		char *q = strtok(NULL, ",");

        int quantity = atoi(q);

		double price = atof(p);
        char *name = malloc(sizeof(char) * 50);
		strcpy(name, n);
		struct Product product = { name, price };
		struct ProductStock stockItem = { product, quantity };
		shop.stock[shop.index++] = stockItem;

       // printf("NAME OF PRODUCT %s PRICE %.2f QUANTITY %d\n", name, price, quantity);

    }
	return shop;
}

//initiate the decision process. Allow user to decide if .csv submitted order is to be used of Live input.
struct Shop decision(){
    int dec1;
    int dec2;
    int dec3;


    printf("Enter 1 if you want to collect pre-submitted shopping list \nor 2 if you want to input your order now:");
    scanf("%d", &dec1);

    if (dec1 == 1){
        printf("\n");
        printf("Enter 1 if you are James, 2 if you are Michael and 3 if you are Mary:");
        scanf("%d", &dec3);
            if (dec3 == 1){
                makeSale();
                printf("===============================\n");
                decision();
                }
            else if (dec3 == 2){
                makeSale3();
                printf("===============================\n");
                decision();
            }
            else if (dec3 == 3){
                makeSale4();
                printf("===============================\n");
                decision();
            }
            else{
        printf("Not a valid selection\n");
        decision();
   }}

   else if (dec1 == 2){
        makeSale2();
        decision();

   }

   else {
        struct Shop shop = createAndStockShop();
        printf("That is not a valid entry, enter 1 if you want to end program \nor 2 to return to the main menu:");
        scanf("%d", &dec2);
        if (dec2 == 2){
            decision();
        }
        else if (
                 dec2 == 1)
        {
        exit(0);}}

}

// initial information delivered to user to outline the products available and price
//(and just for demonstration for this assignment, the stock to show it has been read into the program)
void printShop(struct Shop s)
{
    printf("Welcome to Lost-At-C.com \n");
    printf("\n");
	printf("Starting Cash in Shop: %.2f\n", s.cash);
    printf("\n");
	printf("-- PRODUCT -- PRICE -- STOCK -- \n");

	for (int i = 0; i < s.index; i++)
	{
		printProduct(s.stock[i].product);
		printf("%d\n", s.stock[i].quantity);
			}
    printf("===============================\n");
    printf("\n");

	}


//struct as per tutorial video to find product and retrieve associated price data
double find(struct Shop s, char* name)
{
	for (int i = 0; i < s.index; i++)
	{
		if (strcmp(name, s.stock[i].product.name) == 0){
			return s.stock[i].product.price;
		}
	}
	return -1;
}

//struct to process the sale according to the customers order

//Again, I did try to "feed in" the right createCustomer() output (i.e. .csv file) as an input to makeSale() but I could not get it to run
//so separate (almost identical) makeSales are below - not optimal, but functional for this assignment.
void makeSale() {
        struct Shop shop = createAndStockShop();
        struct Customer customer = createCustomer();

        double totalCostForCustomer = 0;

        printf("--------------------------------------\n");
        printf("Total Cost of Shopping List:\n");
        printf("--PRODUCT -- PRICE -- QUANITY -- TOTAL --\n");

//for all the products in the shopping list, find their price and print the total cost
        for(int i=0; i < 5; i++){
            struct Product p = customer.shoppingList[i].product;
            double price = find(shop, p.name);
            double totalCostOfItem = customer.shoppingList[i].quantity * price;
            totalCostForCustomer += totalCostOfItem;
            int x = (-(strlen(p.name)) + 16); //source: https://www.programiz.com/c-programming/library-function/string.h/strlen
            char* stry = " ";
            //printf("%d  ", y);
            printf("%s %*.2f %4s %03d %5s %.2f\n", p.name, x, price, stry, customer.shoppingList[i].quantity, stry, totalCostOfItem); //source: https://www.codingunit.com/printf-format-specifiers-format-conversions-and-formatted-output
            }

//if statements used to them determine if the sale can go through or...
        if (totalCostForCustomer<customer.budget ){
            printf("Total cost: %.2feuro\n", totalCostForCustomer);
            printf(" \n");
            customer.budget = (customer.budget-totalCostForCustomer);
            printf("%s now have %.2feuro remaining\n", customer.name, customer.budget);
            printf(" \n");
            double totalCashForShop = shop.cash + totalCostForCustomer;
            struct Shop shop = {totalCostForCustomer};
            printf("The updated total cash for the shop is: %.2deuro\n", shop.cash);} //the issue for this lies with shop.cash not being received correctly from the struct



        else{
            printf("Total cost: %.2f\n", totalCostForCustomer);
            printf(" \n");
            printf("It appears you do not have enough money for this purchase,\n");
            printf("please consider your shopping list again. \n");
            printf("\n");
            decision();
        }

//if sale complete, print updated stock levels to demonstrate adjustment.
            printf("\n");
            printf("-- Updated Stock -- \n");
            printf("-- PRODUCT -- PRICE -- STOCK -- \n");

            for (int i = 0; i < shop.index; i++)
            {
                printProduct(shop.stock[i].product);
              //  printf("%d\n", shop.stock[i].quantity);

			  if (strcmp(customer.shoppingList[i].product.name, shop.stock[i].product.name) == 0){
                int stockUpdate = shop.stock[i].quantity - customer.shoppingList[i].quantity;
                printf("%d      \n", stockUpdate);
                updateStock(&stockUpdate);
                //printf("%d\n", shop.stock[i].quantity);

		}}
            printf("\n");

//trigger warning if not enough stock is available
        for (int i = 0; i < shop.index; i++){
        if (shop.stock[i].quantity < customer.shoppingList[i].quantity){
            printf("\n");
            printf("!!!!!!!!!!!!!!!!!!!!! WARNING !!!!!!!!!!!!!!!!!!!!!!!!!\n");
            printf("Unfortunately, as you can see above we cannot fulfil your full order.\n");
            printf("There are only %d %ss\n", shop.stock[i].quantity, shop.stock[i].product.name);
            printf("The last of the stock has been provided for you: %d %s\n",shop.stock[i].quantity, shop.stock[i].product.name);
            printf("%d", shop.stock[i].quantity);
            double overOrderCost = ((customer.shoppingList[i].quantity-shop.stock[i].quantity) *(find(shop, customer.shoppingList[i].product.name)));
            double recalculatedCost = totalCostForCustomer -overOrderCost;
            printf("\n");
            printf("Recalculated cost is: %.2f\n", recalculatedCost);
            printf(" \n");
            customer.budget = customer.budget+overOrderCost;
            printf("%s you now have %.2f remaining\n", customer.name, customer.budget);
            printf(" \n");}}



return 0;}

//use pointer to update the stock in the ProductStock struct, as per tutorial video
void updateStock(struct ProductStock  *p, int q )
  {

     p -> quantity = q;
    //printf("%d\n", q);

     return 0;
  }
//comments as per makeSale()
void makeSale3() {
        struct Shop shop = createAndStockShop();
        struct Customer customer = createCustomer2();

        double totalCostForCustomer = 0;

        printf("--------------------------------------\n");
        printf("Total Cost of Shopping List\n");
        printf("--PRODUCT -- PRICE -- QUANITY -- TOTAL --\n"); //source: https://www.codingunit.com/printf-format-specifiers-format-conversions-and-formatted-output

        for(int i=0; i < 5; i++){

            struct Product p = customer.shoppingList[i].product;
            double price = find(shop, p.name);
            double totalCostOfItem = customer.shoppingList[i].quantity * price;
            totalCostForCustomer += totalCostOfItem;
            int x = (-(strlen(p.name)) + 16); //source: https://www.programiz.com/c-programming/library-function/string.h/strlen
            char* stry = " ";
            printf("%s %*.2f %4s %03d %5s %.2f\n", p.name, x, price, stry, customer.shoppingList[i].quantity, stry, totalCostOfItem); //source: https://www.codingunit.com/printf-format-specifiers-format-conversions-and-formatted-output

           }
        if (totalCostForCustomer<customer.budget ){
            printf("Total cost: %.2f\n", totalCostForCustomer);
            printf(" \n");
            customer.budget = (customer.budget-totalCostForCustomer);
            printf("%s now have %.2f remaining\n", customer.name, customer.budget);
            printf(" \n");
            double totalCashForShop = shop.cash + totalCostForCustomer;
            struct Shop shop = {totalCostForCustomer};
            printf("The updated total cash for the shop is: %.2d\n", shop.cash);}


        else{
            printf("Total cost: %.2f\n", totalCostForCustomer);
            printf(" \n");
            printf("It appears you do not have enough money for this purchase,\n");
            printf("please consider your shopping list again. \n");
            printf("\n");
            decision();
        }
            printf("\n");
            printf("-- Updated Stock -- \n");
            printf("-- PRODUCT -- PRICE -- STOCK -- \n");


        for (int i = 0; i < shop.index; i++)
            {
                printProduct(shop.stock[i].product);

			  if (strcmp(customer.shoppingList[i].product.name, shop.stock[i].product.name) == 0){
                int stockUpdate = shop.stock[i].quantity - customer.shoppingList[i].quantity;
                printf("%d      \n", stockUpdate);
			}}
            printf("\n");

        for (int i = 0; i < shop.index; i++){
        if (shop.stock[i].quantity < customer.shoppingList[i].quantity){
            printf("\n");
            printf("!!!!!!!!!!!!!!!!!!!!! WARNING !!!!!!!!!!!!!!!!!!!!!!!!!\n");
            printf("Unfortunately, as you can see above we cannot fulfil your full order.\n");
            printf("There are only %d %ss\n", shop.stock[i].quantity, shop.stock[i].product.name);
            printf("The last of the stock has been provided for you: %d %s\n",shop.stock[i].quantity, shop.stock[i].product.name);
            printf("%d", shop.stock[i].quantity);
            double overOrderCost = ((customer.shoppingList[i].quantity-shop.stock[i].quantity) *(find(shop, customer.shoppingList[i].product.name)));
            double recalculatedCost = totalCostForCustomer -overOrderCost;
            printf("\n");
            printf("Recalculated cost is: %.2f\n", recalculatedCost);
            printf(" \n");
            customer.budget = customer.budget+overOrderCost;
            printf("%s you now have %.2f remaining\n", customer.name, customer.budget);
            printf(" \n");}}


return 0;}

//comments as per makeSale()
void makeSale4() {
        struct Shop shop = createAndStockShop();
        struct Customer customer = createCustomer3();

        double totalCostForCustomer = 0;

        printf("--------------------------------------\n");
        printf("Total Cost of Shopping List\n");
        printf("--PRODUCT -- PRICE -- QUANITY -- TOTAL --\n"); //source: https://www.codingunit.com/printf-format-specifiers-format-conversions-and-formatted-output

        for(int i=0; i < 5; i++){
            struct Product p = customer.shoppingList[i].product;
            double price = find(shop, p.name);
            double totalCostOfItem = customer.shoppingList[i].quantity * price;
            totalCostForCustomer += totalCostOfItem;
            int x = (-(strlen(p.name)) + 16); //source: https://www.programiz.com/c-programming/library-function/string.h/strlen
            char* stry = " ";
            printf("%s %*.2f %4s %03d %5s %.2f\n", p.name, x, price, stry, customer.shoppingList[i].quantity, stry, totalCostOfItem); //source: https://www.codingunit.com/printf-format-specifiers-format-conversions-and-formatted-output

           }

        if (totalCostForCustomer<customer.budget){
            printf("Total cost: %.2f\n", totalCostForCustomer);
            printf(" \n");
            customer.budget = (customer.budget-totalCostForCustomer);
            printf("%s you now have %.2f remaining\n", customer.name, customer.budget);
            printf(" \n");
            struct Shop shop = {totalCostForCustomer};
            printf("The updated total cash for the shop is: %.2d\n", shop.cash);}

        else{
            printf("Total cost: %.2f\n", totalCostForCustomer);
            printf(" \n");
            printf("It appears you do not have enough money for this purchase,\n");
            printf("please consider your shopping list again. \n");
            printf("\n");
            decision();
        }


            printf("\n");
            printf("-- Updated Stock -- \n");
            printf("-- PRODUCT -- PRICE -- STOCK -- \n");

            for (int i = 0; i < shop.index; i++)
            {
                printProduct(shop.stock[i].product);

			  if (strcmp(customer.shoppingList[i].product.name, shop.stock[i].product.name) == 0){
                int stockUpdate = shop.stock[i].quantity - customer.shoppingList[i].quantity;
                printf("%d      \n", stockUpdate);
                }}

        for (int i = 0; i < shop.index; i++){
        if (shop.stock[i].quantity < customer.shoppingList[i].quantity){
            printf("\n");
            printf("!!!!!!!!!!!!!!!!!!!!! WARNING !!!!!!!!!!!!!!!!!!!!!!!!!\n");
            printf("Unfortunately, as you can see above we cannot fulfil your full order.\n");
            printf("There are only %d %ss\n", shop.stock[i].quantity, shop.stock[i].product.name);
            printf("The last of the stock has been provided for you: %d %s\n",shop.stock[i].quantity, shop.stock[i].product.name);

            double overOrderCost = ((customer.shoppingList[i].quantity-shop.stock[i].quantity) *(find(shop, customer.shoppingList[i].product.name)));
            double recalculatedCost = totalCostForCustomer -overOrderCost;
            printf("\n");
            printf("Recalculated cost is: %.2f\n", recalculatedCost);
            printf(" \n");
            customer.budget = customer.budget+overOrderCost;
            printf("%s you now have %.2f remaining\n", customer.name, customer.budget);
            printf(" \n");}

                printf("\n");}


return 0;}

//this struct is specific to the requirements of the Live Customer.
void makeSale2() {{
        struct Shop shop = createAndStockShop();
        struct LCustomer customer = LiveCustomer();

        double totalCostForCustomer = 0;

//I wanted to apply the processes established in makeSale() to the Live sale also however I could not get the program to overcome a "Null" issue here.
//the code appears to have worked for the .csv customer so I cannot figure out why it would not apply here and after many hours of researching,
//unfortunately had to submit without success.
        for(int i=0; i < 5; i++){
            struct Product p = customer.shoppingList[i].product;
            double price = find(shop, p.name);
            double totalCostOfItem = customer.shoppingList[i].quantity * price;
            totalCostForCustomer += totalCostOfItem;
            int x = (-(strlen(p.name)) + 16); //source: https://www.programiz.com/c-programming/library-function/string.h/strlen
            char* stry = " ";
            printf("%s %*.2f %4s %03d %5s %.2f\n", p.name, x, price, stry, customer.shoppingList[i].quantity, stry, totalCostOfItem); //source: https://www.codingunit.com/printf-format-specifiers-format-conversions-and-formatted-output
     }
        printf("The total cost for the customer will be %.2f\n", totalCostForCustomer);
        printf(" \n", totalCostForCustomer);

        double totalCashForShop = shop.cash + totalCostForCustomer;
        printf("The updated total cash for the shop is: %.2d\n", totalCashForShop);}

/////-> implement makeSale() code to further process Live customer order, update shoppinglist, stock levels and apply if statements to challenge if order can be completed

return 0;}



//Initiate functionality of the program from the "main" method
int main(void)
{
    struct Shop shop = createAndStockShop();
	printShop(shop);

    decision();



   ////////////tutorial  examples////////////////////////


	// struct Customer dominic = { "Dominic", 100.0 };
	//
	// struct Product coke = { "Can Coke", 1.10 };
	// struct Product bread = { "Bread", 0.7 };
	// // printProduct(coke);
	//
	// struct ProductStock cokeStock = { coke, 20 };
	// struct ProductStock breadStock = { bread, 2 };
	//
	// dominic.shoppingList[dominic.index++] = cokeStock;
	// dominic.shoppingList[dominic.index++] = breadStock;
	//
	// printCustomer(dominic);


    // printf("The shop has %d of the product %s\n", cokeStock.quantity, cokeStock.product.name);

    return 0;
}





