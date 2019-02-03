//
//  SecondViewController.swift
//  Lab1
//
//  Created by Jordan Denning on 2/2/19.
//  Copyright © 2019 Jordan Denning. All rights reserved.
//

import UIKit

class SecondViewController: UIViewController {

    @IBAction func gotorecycle(_ sender: UIButton)
    {
        if(UIApplication.shared.canOpenURL(URL(string: "recyclenation://")!))
        {
            UIApplication.shared.open(URL(string: "recyclenation://")!, options: [:], completionHandler: nil)
        }
        else
        {
            UIApplication.shared.open(URL(string:"https://recyclenation.com")!, options: [:], completionHandler: nil)
        }
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }


}

