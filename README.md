# Android-Event-Management-Application

This repository contains an Android application developed for event management, allowing users to insert, view, edit, and delete events. This project was developed as part of the "Mobile Technologies and Applications" (TAM) course at the Escola Superior de Tecnologia e Gestão, Politécnico de Coimbra, during the academic year 2024/25.

## Table of Contents

- [Introduction]
    
- [Features]
    
- [Technologies Used]
    
- [Application Structure]
    
- [Usage Guide]   

- [Results]
    
## Introduction

The primary goal of this project was to develop a functional and intuitive Android application for event management. It focuses on providing a seamless user experience for creating, editing, viewing, and removing events. Key concepts of Android development, such as responsive layouts for portrait and landscape modes, utilization of various UI components, and inter-Activity communication using Intents, were explored and implemented during its development.

## Features

The application implements the following core functionalities:

- **Insert New Events**: Users can create and add new events to the list.
    
- **Event Listing**: Events are displayed in a dynamic list using `RecyclerView`.
    
- **View Single Event**: Detailed viewing of individual event information is available.
    
- **Edit Event Information**: Existing event details can be updated.
    
- **Remove Events**: Events can be deleted from the application.
    
- **Event Enrollment**: Users can enroll in events.
    
- **Graphical Enrollment Display**: Enrollment status is visually presented.
    
- **Filtering**: Events can be filtered by various criteria.
    
- **Activity State Changes**: Proper handling of activity state changes.
    
- **Data Persistence**: Event data is persistently stored using SQLite.
    
- **Inter-Activity Communication**: Intents are used for communication between different Activities.
    

## Technologies Used

To achieve an intuitive and user-friendly interface, various Android Studio graphical components were utilized:

- **Spinners**: For providing predefined lists of options.
    
- **TextViews**: For displaying static information.
    
- **EditText**: For user data input.
    
- **Checkboxes**: For binary choices, such as indicating if an event is free.
    
- **Buttons**: For navigation between activities and executing application actions.
    
- **DatePickers and TimePickers**: To facilitate date and time selection, reducing input errors.
    
- **RecyclerView**: For dynamically displaying the list of events, offering flexibility and performance in data manipulation.
    
- **Intents**: Fundamental tool for communication between Activities, ensuring data transmission and presentation throughout the application navigation.
    
- **SQLite Database**: Used for persistent local data storage, allowing dynamic storage, manipulation, and access to event elements.
    
- **DAO (Data Access Object) Interface**: Defines methods to access and manipulate data, separating business logic from persistence logic.
    
- **Adapter**: Transforms list data into visual components for display in the graphical interface (RecyclerView).
    

## Application Structure

The application's core components are organized into the following classes and layouts:

**Classes:**

- `MainActivity`
    
- `Evento`
    
- `InserirEventoActivity`
    
- `EventoAdapter`
    
- `GestaoBaseDados`
    
- `EventoOpenHelper`
    
- `EventoDAO`
    

**Layouts:**

- `Activity_main.xml` (and `Activity_main.xml (Land)` for landscape)
    
- `Inserir_evento.xml` (and `Inserir_evento.xml (Land)` for landscape)
    
- `Lista_evento.xml` (and `Lista_evento.xml (Land)` for landscape)
    
- `Spinner_lista.xml`
    

**Data Structures:**

- `ArrayList<Evento>`: Used to store all events added to the list.
    
- `Array of String`: Used to store options for the Spinner.
    

## Usage Guide

This section provides a brief overview of how to interact with the application.

### Application Initialization

Upon launching the application, you will be redirected to the homepage. If it's your first time, no events will be listed. The application supports both portrait and landscape orientations.

### Inserting an Event

To add a new event:

1. Click the "Add" button.
    
2. A form will appear for filling in event details.
    
3. Fill in the necessary information. If the event is free, the "Price" field will be disabled.
    

### Viewing Events

Return to the homepage to see a list of all inserted events with their respective information.

### Detailed Viewing and Editing an Event

To view details or edit an event:

1. Click on any event in the listing.
    
2. The event details will be displayed, with fields initially disabled for editing.
    
3. Click the "Edit" button to enable all fields for modification.
    
4. After making changes, click the "Save" button to save them.
    

### Deleting an Event

To delete an event:

1. Follow the same steps as editing an event to access the event form.
    
2. An icon for deleting the event will be presented.
    
3. After deletion, you will be redirected to the homepage to confirm the event's removal.
    

### Buying/Cancelling a Ticket

- To buy a ticket, click the "Buy Ticket" button.
    
- If you have already purchased, click "Cancel purchase" to cancel it.
    

### Applying Filters

Utilize the checkboxes and dropdown menu on the homepage to apply filters to the event listing. You can filter for future events, events you are enrolled in, or by event type.

## Results

![homepage](https://github.com/rafaelgmv/Android-Event-Management-Application/blob/main/img/Captura%20de%20ecr%C3%A3%202025-07-18,%20%C3%A0s%2018.13.10.png?raw=true)

![insertEvent](https://github.com/rafaelgmv/Android-Event-Management-Application/blob/main/img/Captura%20de%20ecr%C3%A3%202025-07-18,%20%C3%A0s%2018.13.29.png?raw=true)

![listEvents](https://github.com/rafaelgmv/Android-Event-Management-Application/blob/main/img/Captura%20de%20ecr%C3%A3%202025-07-18,%20%C3%A0s%2018.14.02.png?raw=true)

![editEvent](https://github.com/rafaelgmv/Android-Event-Management-Application/blob/main/img/Captura%20de%20ecr%C3%A3%202025-07-18,%20%C3%A0s%2018.14.28.png?raw=true)

![buyTicket](https://github.com/rafaelgmv/Android-Event-Management-Application/blob/main/img/Captura%20de%20ecr%C3%A3%202025-07-18,%20%C3%A0s%2018.14.47.png?raw=true)


---
#### License

This project is licensed under the MIT License - see the **LICENSE** file for details.
