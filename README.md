# ITSS Software Development Capstone: EcobikeRental

# 🙆‍♂️ Overview

This repository stores ITSS Software Development course's capstone project.

🚵 **EcobikeRental** is a platform which allows novice users to rent and return bikes inside Ecopark township.

## Project structure

| File/Folder                                                                         | Content                                              |
| ----------------------------------------------------------------------------------- | ---------------------------------------------------- |
| [Programming](Programming/)                                                         | Source code and documentation (Javadoc)              |
| [Production](Production/)                                                           | Executable production files and Installation guides  |
| [EcoBikeRental Diagrams](Requirement%20Analysis/EcoBikeRental%20Diagrams.asta)      | Project diagrams _(activity, class,...)_             |
| [EcoBikeRental_SRS](Requirement%20Analysis/EcoBikeRental_SRS.pdf)                   | Software Requirement Specification                   |
| [ITSS Software Development Capstone](ITSS%20Software%20Development%20Capstone.pptx) | Presentation slides                                  |
| [ClassDiagram](ClassDiagram/)                                                       | General and Detailed class diagrams after production |

# How to install and run ECOB

## Installation requirements

- Software: JDK 15
- Hardware: PC
- Database: Database has already been created

## How to run

Open shell, change to `Production/Product` directory, run:

```shell
java -jar Programming.jar
```

Or open `Production/Product` directory, double click on `Programming.jar`

# 🧑‍💻 Project tracking

## 1️⃣ Lab 01 - Use Case Diagram

### Tasks

- Draw use case diagram
- Outline `Rent Bike`, `View Bike Info` and `Return Bike` use cases.

### Contributions

| Name           | Contribution                      |
| -------------- | --------------------------------- |
| Le Huy Hoang   | `View bike info` outline          |
| Nguyen Vu Minh | `Rent bike` outline               |
| Bui Manh Tu    | `Return bike` outline & README.md |

### Revisions

## 2️⃣ Lab 02 - Software Requirement Specification

### Tasks

- Write `Rent Bike`, `View Bike Info`, `Return Bike`, and `Make Transaction` use case specifications.
- Draw their activity diagrams
- Complete EcoBikeRental SRS document

### Contributions

| Name           | Contribution                                                                  |
| -------------- | ----------------------------------------------------------------------------- |
| Le Huy Hoang   | **1. Introduction** and `View Bike Info` specification                        |
| Nguyen Vu Minh | **2. Overall Description** and `Rent Bike`, `Make Transaction` specifications |
| Bui Manh Tu    | **4. Supplementary Specification** and `Return bike` specification            |

### Revisions

| Name        | Revision                                                                                             |
| ----------- | ---------------------------------------------------------------------------------------------------- |
| Bui Manh Tu | SRS document and [EcoBikeRental Diagrams](Requirement%20Analysis/EcoBikeRental%20Diagrams.asta) file |

## 3️⃣ Lab 03 - Interaction Diagram

### Tasks

- Complete `Rent Bike`, `View Bike Info`, `View Station Info`, `Return Bike`, and `Make Transaction` interaction
  diagrams.

### Contributions

| Name           | Contribution                                      |
| -------------- | ------------------------------------------------- |
| Le Huy Hoang   | `View Bike Info` and `View Station Info` diagrams |
| Nguyen Vu Minh | `Rent Bike` and `Make Transaction` diagrams       |
| Bui Manh Tu    | `Return bike` diagrams                            |

### Revisions

| Name        | Revision                                                                                             |
| ----------- | ---------------------------------------------------------------------------------------------------- |
| Bui Manh Tu | All diagrams and [EcoBikeRental Diagrams](Requirement%20Analysis/EcoBikeRental%20Diagrams.asta) file |

## 5️⃣ Lab 05 - Interface Design

**Important note:** On this lab, every member completes their own work on time. However, due to Bui Manh Tu's late
revision, this lab is not updated accordingly on Github. Please notice this while grading us 😥!

### Tasks

- Design graphical user interface relating to `View station info`, `View bike info`, `Rent bike`, `Return bike`
  , `Make transaction` and other utilities;

- Design Interbank subsystem;

- Complete GUI Design specification document.

### Contributions

| Name           | Contribution                                                |
| -------------- | ----------------------------------------------------------- |
| Le Huy Hoang   | **1. Screen configuration**                                 |
| Nguyen Vu Minh | Interbank subsystem                                         |
| Bui Manh Tu    | **2. Screen transition diagram** and UI theme & base design |

For detailed information on each screen's contribution, please read **4. Detailed screen specifications**
in [GUI Design Specifications](Requirement%20Analysis/Detailed%20Design/Interface%20Design/GUI%20Design%20Specifications.pdf)
document.

### Revisions

| Name           | Revision                                                                                                                                               |
| -------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------ |
| Bui Manh Tu    | [GUI Design Specifications](Requirement%20Analysis/Detailed%20Design/Interface%20Design/GUI%20Design%20Specifications.pdf) document and all UI designs |
| Nguyen Vu Minh | Interbank subsystem                                                                                                                                    |

## 6️⃣ Lab 06 - Class Design

### Tasks

Design classes relating to `View station info`, `View bike info`, `Rent bike`, `Return bike`, `Make transaction`

### Contributions

| Name           | Contribution                                  |
| -------------- | --------------------------------------------- |
| Le Huy Hoang   | `View station info`, `View bike info` classes |
| Nguyen Vu Minh | `Rent bike`, `Make transaction`               |
| Bui Manh Tu    | `Return bike`                                 |

## 7️⃣ Lab 07 - Data Modelling

### Tasks

Design database relating to `View station info`, `View bike info`, `Rent bike`, `Return bike`, `Make transaction`

### Contributions

| Name           | Contribution                       |
| -------------- | ---------------------------------- |
| Le Huy Hoang   | `Bike`, `Station`                  |
| Nguyen Vu Minh | `PaymentTransaction`, `CreditCard` |
| Bui Manh Tu    | `BikeDetails`, `Order`, `Payment`  |

### Revisions

| Name           | Contribution            |
| -------------- | ----------------------- |
| Nguyen Vu Minh | `Bike`, `Station`       |
| Bui Manh Tu    | Other entities & Report |

## 8️⃣ Lab 08 - Unit Test

### Tasks

Design unit test relating to `View station info`, `View bike info`, `Rent bike`, `Return bike`, `Make transaction`

### Contributions

| Name           | Contribution                                  |
| -------------- | --------------------------------------------- |
| Le Huy Hoang   | `ViewStationController`                       |
| Nguyen Vu Minh | `RentBikeController`, `TransactionController` |

### Revisions

| Name        | Contribution |
| ----------- | ------------ |
| Bui Manh Tu | Revision     |

## 🔟 Lab 10 - Good Design

### Tasks

### Contributions

| Name           | Contribution                                    |
| -------------- | ----------------------------------------------- |
| Le Huy Hoang   | `ViewStationInfo`, `ViewBikeInfo` Design Review |
| Nguyen Vu Minh | `RentBike`, `MakeTransaction` Design Review     |

### Revisions

| Name        | Contribution |
| ----------- | ------------ |
| Bui Manh Tu | Everything   |

# 📐 Conventions

The [EcoBikeRental Diagrams](Requirement%20Analysis/EcoBikeRental%20Diagrams.asta) file has all project diagrams _(
activity, class,...)_

## Git convention

- Before doing lab, please `git pull` to update the latest version
- Add a `.gitignore` which specifies files that Git ignores like your local, temporary and log files, _e.g., files and
  folders start with `.` or `_`like`.idea`or`.vscode`\_
- For creating and naming branch, git workflow, please refer to the _Lab00_ class material document.
- Only @theobmgit controls **release** and **main** branch in order to prevent merge hell.
