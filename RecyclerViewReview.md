# RecyclerViews

## The Key Players

* The **RecyclerView** is provided by Android. It's the class responsible for actually handling moving the individual rows of the view around as the user scrolls.

* The **Adapter** is a class we write that tells the RecyclerView how to behave. It does so through the three methods that have to be overridden:
  * `onCreateViewHolder` will be called by the RecyclerView whenever it needs to create a brand-new row of the table. In that method, our Adapter will create that view by inflating a layout file, and it will put that view inside of a ViewHolder (see below) that it returns to the RecyclerView.
  * `onBindViewHolder` will be called by the RecyclerView whenever it wants to take a particular ViewHolder (that we created in a previous call to onCreateViewHolder) and prepare that ViewHolder to be displayed at a particular index of the RecyclerView. In that method, our Adapter will fill in the ViewHolder with the data that should be displayed at that index.
  * `getItemCount` will be called by the RecyclerView when it's trying to figure out how many total rows it might need. In that method, we return... how many rows there should be.

* The **ViewHolder** is a class we write to act as a wrapper around the actual widgets on the page that will be displayed. Our main job is, in the constructor, to save references (as instance variables) to each of the individual views that we'll want to change for each row of the table. We can then use those instance variables within the `onBindViewHolder` method of our Adapter.

## Some Other Related Concepts

* In order to allow users to tap on the rows of our RecyclerView, within the constructor for our ViewHolder, we add an onClick listener to the entire row that will notify the Activity that contains the RecyclerView about which row was tapped on.
