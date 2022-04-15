# io-unzip
You need to proceed to [UnzipFile](src/main/java/com/epam/autocode/io/unzip/UnzipFile.java)
and write a method that will unzip archive and print inner files.
## Description
Method have <b>two</b> String arguments.</br>

    public void unzip(String fileZip, String destination) throws IOException {}

The first path to ZIP archive and the second path to directories where you whant to put unzip files.  

Notice that it throws IOExeption.

As a result you have unzip files in destination directory and printed files names.

You can use <i>resources</i> folder to store your files.

## Example
###Usage

    /**
     * some code before 
     */
    String file = "path/to/my/archive.zip";
    String dest = "path/to/my/directory";

    unzipFile.unzip(file,dest);

#### Only files in ZIP
    Input:
        String path/to/my/archive.zip path/to/my/archive_d.zip

    Output:
        some.png
        randome.mp4
        file.pdf
#### Files and directories in ZIP
    Input:
        path/to/my/archive_d.zip

    Output:
        some.png
        randome.mp4
        file.pdf
        inner_dir/
        inner_dir/1.zip
        inner_dir/2.rar


