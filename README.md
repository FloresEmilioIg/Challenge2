# JSON to CSV Converter - Java Desktop Application

# JSON to CSV Converter - Java Desktop Application

📄 Description

This repository contains a Java program that reads JSON files and exports them to CSV format.  
It includes both a **console version** (`main.java`) and a **desktop GUI version** using Swing (`AppSwing.java`).  

**Purpose:**  
- Read JSON files (`JSONObject` or `JSONArray`).  
- Display content in the console or in a desktop window.  
- Export data to CSV using OpenCSV.  
- Demonstrate exception handling and JavaDoc documentation.

---

🗂 Repository Structure

/Challenge2

  src/
  
    main/
      java/
        main.java # Console version
        AppSwing.java # Swing desktop GUI
        JsonReader.java # JSON reading class
        CSVWrite.java # CSV writing class
        
  data.json # Sample JSON (object)
  
  dataArray.json # Sample JSON (array)
  
  output.csv # Generated CSV file
  
  README.md
  
  pom.xml


⚙ Requirements

- JDK 17 or higher (compatible with Java SE 8+).  
- External libraries:
  - `org.json` → for JSON manipulation.
  - `OpenCSV` → for CSV generation.  

> Both libraries should be included in the classpath or configured as dependencies in IntelliJ or Maven.

---

🚀 How to Run

### 1. Console Version
1. Open the project in IntelliJ IDEA.
2. Run `main.java`.  
3. The program will attempt to read:
   - `data.json` → JSON object or array. 
4. `output.csv` will be generated with the data defined in the code.

### 2. Desktop Version (Swing)
1. Run `AppSwing.java`.  
2. The window allows:
   - **Load JSON:** Select a `.json` file to view its content.  
   - **Export CSV:** Generate `output.csv` from the predefined or loaded data.

---

📑 Documentation
- All classes and methods are documented with JavaDoc.  
- Key functions:
  - `JsonReader.readJsonObject(String filePath)` → reads a JSON object.  
  - `JsonReader.readJsonArray(String filePath)` → reads a JSON array.  
  - `CsvWriter.writeCsv(String filePath, String[][] data)` → writes a CSV file.

---

📝 Additional Notes
- Error handling:
  - If a JSON file does not exist, the program continues normally.  
  - If a JSON file is empty or malformed, an error message is shown.
- Modular structure allows easy addition of new features or export formats.  

---

🔐 Access Permissions
- All files are available for review by the Digital NAO team.  
- No additional configuration is required if the repository is cloned completely.

---

💡 Recommendations
- To test the application, ensure `data.json` contain valid JSON in it's code.  
- To create an executable JAR, use IntelliJ's option:  
  `File → Project Structure → Artifacts → JAR → From modules with dependencies`.
