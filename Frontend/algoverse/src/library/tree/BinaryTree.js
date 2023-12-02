import {options} from "../../config";

class Node {
    static counter = 0;    

    constructor(value, parent = null) {
        this.id = Node.counter;
        Node.counter++;

        this.parent = parent;
        this.value = value;
        this.left = null;
        this.right = null;
        this.valid = 1; // if this node is deleted, then it will be -1.
        this.visited = false;
        this.found = false;
        // this.created = false;
    }

    _convert(item, i = 0) { // 값 설정
        return isNaN(item) ? parseInt(item.charCodeAt(i), 10) : parseInt(item, 10);
    }

    isroot() {
        if(this.parent === null){
            return 1;
        }
        return 0;
    }

    all_clear() {
        this.visited = false;
        this.found = false;
        if(this.left){
            this.left.all_clear();
        }
        if(this.right){
            this.right.all_clear();
        }
    }

    _handleEqual(item, itemValue, thisValue) {
        if (isNaN(item)) {
            let index = 1;
            while (itemValue === thisValue) {
                itemValue = this._convert(item, index);
                thisValue = this._convert(this.value, index);
                index++;

                if (index >= this.value.length) {
                    this.addRightChild(item);
                    return;
                }
                if (index >= item.length) {
                    this.addLeftChild(item);
                    return;
                }
            }
            if (itemValue === thisValue) {
                this.addLeftChild(item)
            } else {
                this._compareValues(item, itemValue, thisValue);
            }

        } else {
            // same number, insert at left child
            this.addLeftChild(item)
        }
    }

    _compareValues(item, itemValue, thisValue) {
        if (itemValue < thisValue) {
            if (this.left) {
                this.left.insert(item);
            } else {
                this.addLeftChild(item);
            }
        } else if (itemValue > thisValue) {
            if (this.right) {
                this.right.insert(item);
            } else {
                this.addRightChild(item);
            }
        } else {
            // Special case for equality (already exists)
            console.log("Value already exists in the tree");
            // You can choose to throw an error, log a message, or handle it as needed
        }
    }

    toSearchGraph(isRoot = true) { // search를 표현하기 위한 것! 
        let edges = this.parent ?
            [
                {
                    from: this.parent.id,
                    to: this.id
                }
            ] : [];
        let nodes = this.value ?
            [
                {
                    id: this.id,
                    label: this.value,
                    shape: isRoot? "box" : options.nodes.shape,
                    color: this.visited? "pink" :options.nodes.color
                }
            ] : [];
        if (this.left) {
            let leftRes = this.left.toSearchGraph(false);
            edges = [...edges, ...leftRes.edges];
            nodes = [...nodes, ...leftRes.nodes];
        }
        if (this.right) {
            let rightRes = this.right.toSearchGraph(false);
            edges = [...edges, ...rightRes.edges];
            nodes = [...nodes, ...rightRes.nodes];
        }

        return {
            nodes,
            edges  
        }   
    } 

    addLeftChild(item) {
        this.visited = true;
        this.left ? this.left.insert(item) : this.left = new Node(item, this);
    }

    addRightChild(item) {
        this.visited = true;
        this.right ? this.right.insert(item) : this.right = new Node(item, this);
    }


    insert(item) {
        let itemValue = this._convert(item);
        this.visted = true;
        
        if (this.value) {
            let thisValue = this._convert(this.value);
            this._compareValues(item, itemValue, thisValue);
        } else {
            this.value = item;
        }
    }

    delete(item) {
        let itemValue = this._convert(item);
    
        if (!this.value) {
            return this; // Tree is empty or the current node is null, return the current node
        }
    
        let thisValue = this._convert(this.value);
    
        if (itemValue < thisValue && this.left) {
            this.left = this.left.delete(item);
            if (this.left) {
                this.left.parent = this;
            }
        } else if (itemValue > thisValue && this.right) {
            this.right = this.right.delete(item);
            if (this.right) {
                this.right.parent = this;
            }
        } else if (itemValue === thisValue) {
            if (!this.left && !this.right) {
                // Case 1: Node with no children
                if (this.isroot() === 1) {
                    // If it's the root, set the value to null and update the parent
                    this.value = null;
                    if (this.parent) {
                        this.parent = null;
                    }
                } else {
                    // If it's not the root, return null to indicate the node should be removed
                    return null;
                }
            } else if (!this.left) {
                // Case 2: Node with one right child
                if (this.isroot() === 1) {
                    this.value = this.right.value;
                    this.right = this.right.delete(this.right.value);
                    if (this.right) {
                        this.right.parent = this;
                    }
                } else {
                    return this.right;
                }
            } else if (!this.right) {
                // Case 3: Node with one left child
                if (this.isroot() === 1) {
                    this.value = this.left.value;
                    this.left = this.left.delete(this.left.value);
                    if (this.left) {
                        this.left.parent = this;
                    }
                } else {
                    return this.left;
                }
            } else {
                // Case 4: Node with two children
                let minValueNode = this._findMinNode(this.right);
                this.value = minValueNode.value;
                this.right = this.right.delete(minValueNode.value);
                if (this.right) {
                    this.right.parent = this;
                }
            }
        }
        return this;
    }   

    _findMinNode(node) {
        // Helper function to find the node with the minimum value in a subtree
        while (node.left) {
            node = node.left;
        }
        return node;
    }

    toGraph(isRoot = true) { // 첫 노드에서 시작! 노드 하나하나씩 표현
        let color = null;
        if (this.found === true){
            color = "skyblue";
        } else if (this.visited === true){
            color = "pink";
        }
        let edges = this.parent ?
            [
                {
                    from: this.parent.id,
                    to: this.id
                }
            ] : [];
        let nodes = this.value ?
            [
                {
                    id: this.id,
                    label: this.value,
                    shape: isRoot? "box" : options.nodes.shape,
                    color: color? color : options.nodes.color
                }
            ] : [];
        if (this.left) {
            let leftRes = this.left.toGraph(false);
            edges = [...edges, ...leftRes.edges];
            nodes = [...nodes, ...leftRes.nodes];
        }
        if (this.right) {
            let rightRes = this.right.toGraph(false);
            edges = [...edges, ...rightRes.edges];
            nodes = [...nodes, ...rightRes.nodes];
        }

        return {
            nodes,
            edges  
        } 
    }

    delay(ms) {
        return new Promise(resolve => setTimeout(resolve, ms));
    }

    binarySearch(item){
        let itemValue = this._convert(item);
        let thisValue = this._convert(this.value);

        if(!this.visited) { // 
            this.visited = true;
            return -1;
        }
        else {
            if (thisValue > itemValue) { // to the right
                console.log("to right");
                if(this.left) {
                    return this.left.binarySearch(item); 
                }
                else { 
                    return 1; }
            }
            else if(thisValue < itemValue) { // to the left
                console.log("to left");
                if(this.right) {
                    return this.right.binarySearch(item); 
                } else { return 1; }
            }
            else if (thisValue === itemValue) {
                console.log("found it");
                this.found = true;   
                return 1;
            }
        }
    }
  
}

export {Node as BinaryTree};