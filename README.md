<!DOCTYPE html>
<html>

  <body>
    <header>
      <h1>JSON to CSV Converter</h1>
    </header>
    <main>
      <h2>Overview</h2>
      <p>
        This Java program converts JSON data to CSV format. It extracts column
        names automatically from the JSON data, including nested objects and
        arrays, and creates a CSV file with one row of header names and one row
        of data values.
      </p>
      <h2>Requirements</h2>
      <ul>
        <li>Java Development Kit (JDK) version 8 or later</li>
        <li>Maven build system</li>
      </ul>
      <h2>Usage</h2>
      <ol>
        <li>
          Clone this repository to your local machine:
          <pre><code>git clone https://github.com/ugurinanmaz/JSONtoDot.git</code></pre>
        </li>
        <li>
          Navigate to the project directory in a terminal or command prompt:
          <pre><code>cd JSONtoCSV</code></pre>
        </li>
        <li>
          Run <code>mvn clean package</code> to build the project and create an
          executable JAR file:
          <pre><code>mvn clean package</code></pre>
        </li>
        <li>
          Run the JAR file with the following command to convert the JSON data in
          <code>src/main/java/datasoruce/jsondata.json</code> to CSV format and
          save the output to <code>src/main/java/datasoruce/jsondata.csv</code>:
          <pre><code>java -jar target/json-to-csv-converter-1.0-SNAPSHOT.jar</code></pre>
        </li>
      </ol>
      <h2>Example</h2>
       <p>Here's an example of
         </p>
<table>
<thead>
<tr>
<th>name</th>
<th>age</th>
<th>email</th>
<th>address.street</th>
<th>address.city</th>
<th>address.state</th>
<th>address.zip</th>
<th>phone_numbers[0].type</th>
<th>phone_numbers[0].number</th>
<th>phone_numbers[1].type</th>
<th>phone_numbers[1].number</th>
</tr>
</thead>
<tbody>
<tr>
<td>John Doe</td>
<td>30</td>
<td>johndoe@example.com</td>
<td>123 Main St</td>
<td>Anytown</td>
<td>CA</td>
<td>12345</td>
<td>home</td>
<td>555-1234</td>
<td>work</td>
<td>555-5678</td>
</tr>
</tbody>
</table>
<h2>Credits</h2>
<p>This program was created by Ugur Inanmaz for educational purposes.</p>
</main>

  </body>
</html>
