//
//  SearchResultsControllerTableViewController.swift
//  Lab2
//
//  Created by Jordan Denning on 2/13/19.
//  Copyright © 2019 Jordan Denning. All rights reserved.
//

import UIKit

class SearchResultsControllerTableViewController: UITableViewController, UISearchResultsUpdating {
    
    var allwords = [String]()
    var filteredWords = [String]()
    
    
    func updateSearchResults(for searchController: UISearchController) {
        let searchString = searchController.searchBar.text
        filteredWords.removeAll(keepingCapacity: true)
        if searchString?.isEmpty == false {
            let searchfilter: (String) -> Bool = { name in let range = name.range(of: searchString!, options: .caseInsensitive)
                    return range != nil
            }
            let matches = allwords.filter(searchfilter)
            filteredWords.append(contentsOf: matches)
        }
        tableView.reloadData()
    }
    

    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: "TeamID")

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return filteredWords.count
    }

    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "TeamID", for: indexPath)
        cell.textLabel?.text = filteredWords[indexPath.row]


        return cell
    }

}
