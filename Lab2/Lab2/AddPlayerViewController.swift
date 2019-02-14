//
//  AddPlayerViewController.swift
//  Lab2
//
//  Created by Jordan Denning on 2/13/19.
//  Copyright © 2019 Jordan Denning. All rights reserved.
//

import UIKit

class AddPlayerViewController: UIViewController {

    @IBOutlet weak var playerTextfield: UITextField!
    var addedPlayer = String()
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "doneSegue"{
            if playerTextfield.text?.isEmpty == false{
                addedPlayer = playerTextfield.text!
            }
        }
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
