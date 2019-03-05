//
//  ViewController.swift
//  NameDay
//
//  Created by Jordan Denning on 3/4/19.
//  Copyright © 2019 Jordan Denning. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    var nameText = String()
    var month = Int()
    var day = Int()
    @IBOutlet weak var commentLabel: UILabel!
    @IBOutlet weak var monthLabel: UILabel!
    @IBOutlet weak var dayLabel: UILabel!
    @IBOutlet weak var nameField: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
//        loadjson("jordan")
    }
    
    @IBAction func displayData(_ sender: UIButton) {
        if nameField.text?.isEmpty == false
        {
            nameText = nameField.text!
            loadjson(nameText)
        }
        else
        {
            //create a UIAlertController object
            let alert=UIAlertController(title: "Warning", message: "Please Enter a Name", preferredStyle: UIAlertController.Style.alert)
            //create a UIAlertAction object for the button
            let cancelAction=UIAlertAction(title: "Cancel", style:UIAlertAction.Style.cancel, handler: nil)
            alert.addAction(cancelAction) //adds the alert action to the alert object
            let okAction=UIAlertAction(title: "Okay", style: UIAlertAction.Style.default, handler: {action in
                self.nameField.text="Bob"
            })
            alert.addAction(okAction)
            present(alert, animated: true, completion: nil)
        }
    }
    

    
    func loadjson(_ name: String){
        let urlPath = "https://api.abalin.net/get/getdate?name="+name+"&calendar=us"
        print(urlPath)
        guard let url = URL(string: urlPath)
            else {
                print("url error")
                return
        }
        
        let session = URLSession.shared.dataTask(with: url, completionHandler: {(data, response, error) in
            let httpResponse = response as! HTTPURLResponse
            let statusCode = httpResponse.statusCode
            guard statusCode == 200
                else {
                    print("file download error")
                    return
            }
            //download successful
            print("download complete")
            //parse json asynchronously
            DispatchQueue.main.async {self.parsejson(data!)}
        })
        //must call resume to run session
        session.resume()
    }
    
    func parsejson(_ data: Data){
        var nameData = NameData()
        do
        {
            let api = try JSONDecoder().decode(NameData.self, from: data)
            print("HELLO")
            for nameStuff in api.results
            {
                nameData.results.append(nameStuff)
                print(nameStuff.day)
                
            }
//            print(nameData.results[0].day)
        }
        catch let jsonErr
        {
            print("json error")
            print(jsonErr.localizedDescription)
            return
        }
        //reload the table data after the json data has been downloaded
        print("success")
        loadData(nameData)
    }
    
    
    func loadData(_ array: NameData){
        nameField.text = ""
    if array.results.count != 0
    {
        let output = array.results[0]
        
        month = output.month
        day = output.day
        dayLabel.text = String(day)
            switch(month)
            {

            case 1:
                monthLabel.text = "January"
            case 2:
                monthLabel.text = "February"
            case 3:
                monthLabel.text = "March"
            case 4:
                monthLabel.text = "April"
            case 5:
                monthLabel.text = "May"
            case 6:
                monthLabel.text = "June"
            case 7:
                monthLabel.text = "July"
            case 8:
                monthLabel.text = "August"
            case 9:
                monthLabel.text = "September"
            case 10:
                monthLabel.text = "October"
            case 11:
                monthLabel.text = "November"
            case 12:
                monthLabel.text = "December"
            default:
                monthLabel.text = "January"
            }

        commentLabel.text = nameText + "'s name day is"
    }
    else{
            commentLabel.text = "No days for this name. Enter a new name."
            monthLabel.text = ""
            dayLabel.text = ""
        }

    }

}



