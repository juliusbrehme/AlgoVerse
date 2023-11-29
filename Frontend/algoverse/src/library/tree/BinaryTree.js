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
        this.visited = false;
        this.created = false;
    }

    _convert(item, i = 0) { // 값 설정
        return isNaN(item) ? parseInt(item.charCodeAt(i), 10) : parseInt(item, 10);
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

    addLeftChild(item) {
        this.left ? this.left.insert(item) : this.left = new Node(item, this);
    }

    addRightChild(item) {
        this.right ? this.right.insert(item) : this.right = new Node(item, this);
    }

    insert(item) {
        let itemValue = this._convert(item);
        if (this.value) {
            let thisValue = this._convert(this.value);
            this._compareValues(item, itemValue, thisValue);
        } else {
            this.value = item;
        }
    }

    delete(item) { // 완성 root가 자식이 하나만 있따면? 아니면 없다면?
        let itemValue = this._convert(item);
    
        if (!this.value) {
            return this; // 트리가 비어있거나 현재 노드가 null일 경우, 현재 노드를 그대로 반환
        }
    
        let thisValue = this._convert(this.value);
    
        if (itemValue < thisValue && this.left) {
            this.left = this.left.delete(item);
            if(this.left!=null){ this.left.parent = this; }
        } else if (itemValue > thisValue && this.right) {
            this.right = this.right.delete(item);
            if(this.right!=null){ this.right.parent = this; }
        } else if (itemValue === thisValue) {
            // 입력값과 현재 노드의 값이 일치하는 경우에만 삭제 수행
    
            if (!this.left && this.right) {
                // Case 1: 오른쪽 자식이나 자식이 없는 경우
                return this.right;
            } else if (!this.right && this.left) {
                // Case 2: 왼쪽 자식만 있는 경우
                return this.left;
            } else if (!this.right && !this.left) {
                return null;
            }

            // Case 3: 양쪽 자식이 모두 있는 경우
            let minValueNode = this._findMinNode(this.right);
            this.value = minValueNode.value;
            this.right = this.right.delete(minValueNode.value);
            if(this.right!=null){ this.right.parent = this; }
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

    toGraph(isRoot = true) { // 마지막에 색깔 딱 이렇게..
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
                    shape: isRoot? "box" : options.nodes.shape
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

  
}

export {Node as BinaryTree};