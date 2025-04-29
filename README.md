# Yousty

This project is a backend for one graduation project.

## Table of Contents

- [Installation and Launch](#installation-and-launch)
- [Usage](#usage)
- [License](#license)

## Installation and Launch

### Prerequisites

Before starting, ensure the following tools are installed:
- [JDK 17 или выше](https://adoptium.net/temurin/releases/)

### Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/itltcanz/Yousty.git
    cd Yousty
    ```

2. Download pytorch_model.bin from:
    ```
   https://huggingface.co/mattmdjaga/segformer_b2_clothes/blob/main/pytorch_model.bin
    ```
   and paste to
    ```
   python/resources/model
    ```

   Installation is now complete.

### Launch

Run the project using the built JAR file:

   ```bash
   java -jar build/libs/Yousty-1.0.0.jar
   ```

## Usage

## License

This project is licensed under the MIT License. Details can be found in the LICENSE.md file.
