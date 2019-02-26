//
//  MasterViewController.swift
//  ProjectPrototype
//
//  Created by Jordan Denning on 2/24/19.
//  Copyright © 2019 Jordan Denning. All rights reserved.
//

import UIKit

class MasterViewController: UITableViewController {

    var detailViewController: DetailViewController? = nil
    var wineData = WineDataController()
    var wines = [WineList]()
    var wineNames = [String]()
    var searchController = UISearchController()

    func  render() {
        wines = wineData.getWines()
        for wineType in wines{
            wineNames.append(wineType.name)
        }

        tableView.reloadData()
        
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        wineData.onDataUpdate = {[weak self] (data: [WineList]) in self?.render()}
        wineData.loadJson()
        

        
        let resultsController = SearchResultsController()
        resultsController.allWines = wineNames
        searchController = UISearchController(searchResultsController: resultsController)
        tableView.tableHeaderView = searchController.searchBar
        searchController.searchResultsUpdater = resultsController
        searchController.searchBar.placeholder = "Search for wine"
        
        
        
    }

    override func viewWillAppear(_ animated: Bool) {
        clearsSelectionOnViewWillAppear = splitViewController!.isCollapsed
        super.viewWillAppear(animated)
    }
    

    
    

    // MARK: - Segues

    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "showDetail" {
            if let indexPath = self.tableView.indexPathForSelectedRow {
                let wine = wines[indexPath.row]
                let name = wine.name
                let region = wine.region
                let varietal = wine.varietal
                let vintage = wine.vintage
                let price = wine.price
                let winery = wine.winery
                
                
                let controller = (segue.destination as! UINavigationController).topViewController as! DetailViewController
                controller.title = name
                controller.labelName = name
                controller.labelRegion = region
                controller.labelPrice = price
                controller.labelWinery = winery
                controller.labelVintage = vintage
                controller.labelVarietal = varietal
                controller.navigationItem.leftBarButtonItem = splitViewController?.displayModeButtonItem
                controller.navigationItem.leftItemsSupplementBackButton = true
            }
        }
    }

    // MARK: - Table View

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return wines.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "Cell", for: indexPath)
        cell.textLabel!.text = wineNames[indexPath.row]
//        cell.detailTextLabel!.text = wine.winery
        return cell
    }

//    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
//        // Return false if you do not want the specified item to be editable.
//        return true
//    }

//    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
//        if editingStyle == .delete {
//            objects.remove(at: indexPath.row)
//            tableView.deleteRows(at: [indexPath], with: .fade)
//        } else if editingStyle == .insert {
//            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view.
//        }
//    }


}

