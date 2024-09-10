# Fetch Rewards Coding Exercise - Android App

## Screenshots
<img width="365" alt="Screenshot 2024-09-10 at 12 45 04 PM" src="https://github.com/user-attachments/assets/7f1bfdf7-c665-48af-881c-634a6dd03ec3">
<img width="357" alt="Screenshot 2024-09-10 at 12 45 14 PM" src="https://github.com/user-attachments/assets/56d64912-91b2-49f1-a1b1-b734614f2e68">

## Overview
### This Android application is built to complete the coding exercise provided by Fetch Rewards. The app fetches a list of items from an API, groups the items by listId, filters out any items where name is blank or null, and displays the results in a well-organized and easy-to-read format. The items are sorted first by listId, then by name.

## Features
- Fetching Data: Retrieves data from the Fetch Rewards API.
- Data Filtering: Filters out items with blank or null names.
- Data Grouping: Groups the items by listId.
- Data Sorting: Sorts the data first by listId, then by name.
- Expandable List: Allows users to collapse/expand the grouped list for easier navigation.
- Error Handling: Displays an error message in case of a network failure.
- Loading State: Shows a loading spinner while fetching data from the API.
- Empty State: Displays a message if no items are found after filtering.

## Project Structure
### The project follows MVVM architecture to separate concerns between UI and data handling:
- ItemViewModel.kt: Handles data fetching and business logic.
- ApiService.kt: Defines the Retrofit service to fetch data from the API.
- ItemListScreen.kt: The UI layer that displays the grouped items.
- Item.kt: Data model for the items fetched from the API.

## Setup and Installation
## Prerequisites
### Android Studio (Latest stable version)
### Android SDK (API level 30 or above)
### Internet connection for fetching data from the API
## Steps to Run
### Clone the repository:

### git clone https://github.com/yourusername/fetch-rewards-coding-exercise.git
### cd fetch-rewards-coding-exercise
### Open the project in Android Studio:

## Open Android Studio.
Click on "Open an existing project" and select the folder where you cloned the repository.

## Build the project:

Click on the "Build" menu and select "Rebuild Project" to ensure all dependencies are installed.

## Run the app:

Click on the "Run" button in Android Studio to deploy the app on an emulator or a physical device.
Running Unit Tests

### To run the unit tests:
./gradlew test

### For UI tests:
./gradlew connectedAndroidTest
Alternatively, you can run the tests directly from Android Studio by navigating to the test classes and clicking the "Run" icon.

### Testing
The app includes unit tests for the ItemViewModel to ensure that:

Data is fetched and filtered correctly.
The items are grouped and sorted as expected.

You can find the test cases under com/example/fetchproject/'androidTest'|'test'.

## Example Test Case:
## ItemViewModelTest.kt: Tests for ensuring the proper fetching, filtering, and grouping of the items.
### Libraries Used
- Retrofit: For network operations and API calls.
- Coroutines: For handling asynchronous tasks like network requests.
- Mockk: For mocking and testing the ViewModel.
- Jetpack Compose: For building the UI.
### Decisions & Assumptions
- Filtering logic: Items with blank or null names are filtered out before grouping and displaying.
- Sorting: Items are sorted by listId first, followed by name.
- Expandable Lists: Grouped items are displayed in an expandable format to make navigation easier for the user.
### Future Improvements
- Pagination: Implement pagination for handling larger datasets efficiently.
- Caching: Add local caching to ensure offline availability and quicker access.
- Animations: Implement smooth transitions and animations when expanding/collapsing the groups.
- Enhanced Error Handling: Display more informative error messages, such as retry options when fetching data fails.
