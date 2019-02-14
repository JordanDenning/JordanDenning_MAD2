//
//  PlayersTableViewController.swift
//  Lab2
//
//  Created by Jordan Denning on 2/13/19.
//  Copyright © 2019 Jordan Denning. All rights reserved.
//

import UIKit

class PlayersTableViewController: UITableViewController {
    
    var teamsData = TeamsDataModelController()
    var selectedTeam = 0
    var playerList = [String]()
    var searchController = UISearchController()
    
    @IBAction func unwindSegue (_ segue:UIStoryboardSegue){
        if segue.identifier == "doneSegue"{
            let source = segue.source as! AddPlayerViewController
            if source.addedPlayer.isEmpty == false{
                teamsData.addPlayer(index: selectedTeam, newPlayer: source.addedPlayer, newIndex: playerList.count)
                playerList.append(source.addedPlayer)
                tableView.reloadData()
            }
        }
    }

    override func viewWillAppear(_ animated: Bool) {
        playerList = teamsData.getPlayers(index: selectedTeam)
        let resultsController = SearchResultsControllerTableViewController()
        resultsController.allwords = playerList
        searchController = UISearchController(searchResultsController: resultsController)
        
        searchController.searchBar.placeholder = "Search for a player"
        searchController.searchBar.sizeToFit()
        tableView.tableHeaderView = searchController.searchBar
        searchController.searchResultsUpdater = resultsController
    }
    
    override func viewDidLoad() {
    

//        self.navigationItem.rightBarButtonItem = self.editButtonIte
    }
    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return playerList.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "TeamID", for: indexPath)

        cell.textLabel?.text = playerList[indexPath.row]

        return cell
    }
 

    
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
 

    
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
            playerList.remove(at: indexPath.row)
            teamsData.deletePlayer(index: selectedTeam, playerIndex: indexPath.row)
            tableView.deleteRows(at: [indexPath], with: .fade)
        }
        else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        return true
    }
    
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {
        let fromRow = fromIndexPath.row
        let toRow = to.row
        let movePlayer = playerList[fromRow]
        
        playerList.swapAt(fromRow, toRow)
        
        teamsData.deletePlayer(index: selectedTeam, playerIndex: fromRow)
        teamsData.addPlayer(index: selectedTeam, newPlayer: movePlayer, newIndex: toRow)
    }
    

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

}
