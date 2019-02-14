//
//  TableViewController.swift
//  Lab2
//
//  Created by Jordan Denning on 2/13/19.
//  Copyright © 2019 Jordan Denning. All rights reserved.
//

import UIKit

class TableViewController: UITableViewController {
    
    var teamList = [String]()
    var teamData = TeamsDataModelController()
    

    override func viewDidLoad() {
        super.viewDidLoad()
        teamData.loadData()
        teamList = teamData.getTeam()
        let app = UIApplication.shared
        NotificationCenter.default.addObserver(self, selector: #selector(TableViewController.applicationWillResignActive(_:)), name: UIApplication.willResignActiveNotification, object: app)
        
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
    
    

    }

//     MARK: - Table view data source

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return teamList.count
    }

    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "TeamID", for: indexPath)
        cell.textLabel?.text = teamList[indexPath.row]

        // Configure the cell...

        return cell
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "PlayerSegue"{
            let detailVC = segue.destination as! PlayersTableViewController
            let indexPath = tableView.indexPath(for: sender as! UITableViewCell)!
            detailVC.title = teamList[indexPath.row]
            detailVC.teamsData = teamData
            detailVC.selectedTeam = indexPath.row
        }
    }
 

    /*
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    */

    /*
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    */

    /*
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */
    
    @objc func applicationWillResignActive(_ notification: NSNotification){
        teamData.writeData()
    }

}
