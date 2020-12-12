Marvel Comics
=============

Uses RoomDatabase, OkHttp, Navigation components, RecyclerView to showcase Marvel Comics Data from
[Marvel Developer Portal](https://developer.marvel.com/)

Introduction
------------

This application shows how to use RoomDatabase together with OkHttp to store and display data using
a local database for cache storage and the [Marvel Comics API](https://developer.marvel.com/) as the
remote data source. The app will use a custom repository class to hide the two data source implementations
and provide the data seamlessly to the calling activities/fragments for display.

By using meta-data provided by the api and state values from the Android Components ) the repository will
determine the data-source, fetch (if required ) the data, store and display while the user is using the app.
The local-storage will be update on runtime and with a BackgroundService.

Navigation is based on the BottomBar and Drawer Navigation paradigms. The UI is built with the known
Android layouts and Views, including reusable RecyclerViews with custom adapters, CardViews, ViewPagers,
ContextMenus etc.

Getting Started
---------------

Create a new Project Named Marvel Comics with the Bottom Navigation Activity.

- Move the Broadcast receivers from the previous project
- Move the MyApplication class from the previous project
- Make changes for the Navigation Drawer
- Create the repository package and copy the MarvelService class
- Test the Service and the 3 endpoints( characters, comics, creators)
- RoomDatabase
- MarvelRepository
- Store and update the database.
- Check the stored state and update if needed.
- Using Foreground Service to update the storage (Adds Notification).
- ViewModel and LiveData and architecture aware components.
- Activity/Fragment <-- bind --> ViewModel.

Your Turn
---------

Additional Activities:

- Create two new activities that will display all attributes from a single entry of each of our lists:
characters, comics (CharacterActivity, ComicActivity)
- The activities should be launched when a User clicks on an entry from the corresponding list,
the entries values should be passed on to the child activity.
e.g.  user clicks a character -----> CharacterActivity is launched and displays all Character values
      user clicks a comic -----> ComicActivity is launched and displays all Comic values
- Create the user-profile activity:
    * The profile activity should be available from the NavigationDrawer.
    * The activity must provide lateral navigation with tabs (Hint: ViewPager, PagerAdapter)
    * The activity must have three tabs : profile, favorites, wish-list.
- The profile tab must show:
    * User information in various Views and ViewTypes (You can use hardcoded values, dummy values from the db etc...), get Creative use anything you like :)
    * A Carousel with every creator the user has attributed.
- The Favorites tab must show the List of the user's favorite characters.
- The wish-list tab must show all comics the user has added to his wish-list.
- For the two new tabs you need to create the Room, ViewModel, Repository stack by yourselves. ( Networking is not requred )

User Interaction:

- The User must be able to add comics to his wish-list:
    * from a Context Menu on each entry from the main activity's list
    * from an icon on the on each entry from the List activity.
    * from a FloatingActionButton in the SingleItemActivity you created in the first step of the previous section (ComicActivity)
- The User must be able to add characters to his favorites (Hint: Context menu on singleEntryActivity)
    * from a Context Menu on each entry from the main activity's list
    * from an icon on the on each entry from the List activity.
    * from a FloatingActionButton in the SingleItemActivity you created in the first step of the previous section (CharacterActivity)
- The User must be able to remove items from the favorites list, wish-list, attributions.
    * from a Context Menu on each entry of at the Profile page.

Attributions
------------

<a href="http://marvel.com">Data provided by Marvel. © 2020 MARVEL</a>


