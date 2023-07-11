package com.appsepehr.cryptoanalyzer.utils

import android.os.Environment
import com.appsepehr.cryptoanalyzer.data.model.Crypto
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.Calendar


suspend fun exportToExcelFile(
    cryptoList: List<Crypto>,
    onSuccess: () -> Unit = {},
    onFail: (String) -> Unit = {}
) {
    val workbook = createWorkbook(cryptoList);
    createExcelFile(workbook, onSuccess, onFail)
}

private suspend fun createExcelFile(ourWorkbook: Workbook, onSuccess: () -> Unit, onFail: (String) -> Unit) {

    //get our app file directory


    val ourAppFileDirectory =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
    //Check whether it exists or not, and create one if it does not exist.
    if (ourAppFileDirectory != null && !ourAppFileDirectory.exists()) {
        ourAppFileDirectory.mkdirs()
    }

    //Create an excel file called test.xlsx
    val excelFile = File(ourAppFileDirectory, createFileName() + ".xlsx")

    //Write a workbook to the file using a file outputstream
    try {
        val fileOut = FileOutputStream(excelFile)
        ourWorkbook.write(fileOut)
        fileOut.close()
        onSuccess.invoke()
    } catch (e: FileNotFoundException) {
        onFail("File Not Found")
        e.printStackTrace()
    } catch (e: IOException) {
        onFail(e.message.toString())
        e.printStackTrace()
    }
}

private fun createFileName(): String {
    return formatDate(Calendar.getInstance().time.time).replace(":", ";") + "-Crypto"
}

private fun createWorkbook(cryptoList: List<Crypto>): Workbook {
    // Creating a workbook object from the XSSFWorkbook() class
    val ourWorkbook = XSSFWorkbook()

    //Creating a sheet called "statSheet" inside the workbook and then add data to it
    val sheet: Sheet = ourWorkbook.createSheet("crypto")
    addData(sheet, cryptoList)

    return ourWorkbook
}

private fun createCell(sheetRow: Row, columnIndex: Int, cellValue: String?) {
    //create a cell at a passed in index
    val ourCell = sheetRow.createCell(columnIndex)
    //add the value to it
    //a cell can be empty. That's why its nullable
    ourCell?.setCellValue(cellValue)
}

private fun addData(sheet: Sheet, cryptoList: List<Crypto>) {
    addTitleRowToSheet(sheet)

    cryptoList.forEachIndexed { i, crypto ->
        val row = sheet.createRow(i + 1)
        createCell(row, 0, crypto.name)
        createCell(row, 1, crypto.chain)
        createCell(row, 2, crypto.token)
        createCell(row, 3, crypto.volume.toString())
        createCell(row, 4, "$" + crypto.price.toString())
        createCell(row, 5, formatPriceDiff(crypto.priceDiff))
        createCell(row, 6, crypto.logoLink)
        createCell(row, 7, crypto.creationTime)
        createCell(row, 8, crypto.score.toString())
        createCell(row, 9, crypto.link)
    }

}

private fun addTitleRowToSheet(sheet: Sheet) {
    val titleRow = sheet.createRow(0)
    createCell(titleRow, 0, "Name")
    createCell(titleRow, 1, "Chain")
    createCell(titleRow, 2, "Token")
    createCell(titleRow, 3, "Volume")
    createCell(titleRow, 4, "Price")
    createCell(titleRow, 5, "PriceDiff")
    createCell(titleRow, 6, "LogoLink")
    createCell(titleRow, 7, "CreationTime")
    createCell(titleRow, 8, "Score")
    createCell(titleRow, 9, "Link")
}
