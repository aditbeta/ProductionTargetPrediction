# ProductionTargetPrediction

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/othneildrew/Best-README-Template">
    <img src="src/view/icon/logo.png" alt="Logo" width="650" height="150">
  </a>
  <h3 align="center">Production Target Prediction</h3>
  <p align="center">
    Production target prediction generator based on data mining.
  </p>
</div>



<!-- GETTING STARTED -->
## Getting Started

Step by step about how to use this program

### Prerequisites

Things you need to use, the software and how to install them.
* [![Java][Java-img]][Java-url]
* [![MySQL][MySQL-img]][MySQL-url]
* [![IntelliJ][IntelliJ-img]][IntelliJ-url]

### Installation

_Below is an example of how you can instruct your audience on installing and setting up your app. This template doesn't rely on any external dependencies or services._

1. Clone the repo
   ```sh
   git clone https://github.com/aditbeta/ProductionTargetPrediction.git
   ```
2. Install Java ([jdk-20](https://www.oracle.com/java/technologies/downloads/#jdk20-windows))
3. Install MySQL ([8.0.33](https://dev.mysql.com/downloads/windows/installer/8.0.html))
4. Install [IntelliJ](https://www.jetbrains.com/idea/download) (or any other preferred IDE)



<!-- USAGE EXAMPLES -->
## Usage

1. Create MySQL username & password when installing
2. Initialize database by importing the sql file at [sql/init.sql](https://github.com/aditbeta/ProductionTargetPrediction/blob/master/src/sql/init.sql)
3. Seed the database if needed by importing the seed sql file at [sql/seed.sql](https://github.com/aditbeta/ProductionTargetPrediction/blob/master/src/sql/seed.sql)
4. Run the project via IDE or use this command
   ```sh
   java -cp "pathTo\lib\mysql-connector-j-8.0.33.jar;pathTo\out\production\ProductionTargetPrediction" Main
   ```



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[product-screenshot]: images/screenshot.png
[Java-img]: https://cdn.icon-icons.com/icons2/2530/PNG/512/java_button_icon_151928.png
[Java-url]: https://www.oracle.com/java/technologies/downloads/#jdk20-windows
[MySQL-img]: https://cdn.icon-icons.com/icons2/2699/PNG/512/mysql_official_logo_icon_169938.png
[MySQL-url]: https://dev.mysql.com/downloads/windows/installer/8.0.html
[IntelliJ-img]: https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/IntelliJ_IDEA_Icon.svg/512px-IntelliJ_IDEA_Icon.svg.png?20200803071016
[IntelliJ-url]: https://www.jetbrains.com/idea/download
