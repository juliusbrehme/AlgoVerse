import React, { useCallback, useEffect, useState } from "react";
import Graph from "react-graph-vis";
import InputForm from './InputForm';
import { BinaryTree } from "../../library/tree/BinaryTree";
import Col from "react-bootstrap/Col";
import { options } from "../../config";    
import Row from "react-bootstrap/Row";
import { IconTrash } from "./Icons";
import '../../style/treeForm.css';

const initialNodeValues = ['10', '6', '3', '9', '15', '13', '20'];

const TreeGraph = (props) => {
    const emptyTree = {
        edges: [],
        nodes: []
    };

    const [tree, setTree] = useState(new BinaryTree(null));
    const [representation, setRepresentation] = useState({
        edges: [],
        nodes: []
    });
    const [network, setNetwork] = useState(null);
    const [divElement, setDiv] = useState(null);

    const handleResize = useCallback(() => {
        if (network) {
            let newOptions = options;
            newOptions.height = `${divElement.clientHeight}px`;
            network.setOptions(newOptions);
            network.fit();
        }
    }, [network, divElement]);

    useEffect(() => {
        // Set up the initial tree when the component mounts
        const initialTree = new BinaryTree(null);
        initialNodeValues.forEach(value => initialTree.insert(value));
        setTree(initialTree);
    
        // Convert the initial tree to graph format and update the representation
        const initialGraph = initialTree.toGraph();
        setRepresentation(initialGraph);
        if (network) {
            network.setData(initialGraph);
        }
    
        window.addEventListener('resize', handleResize);
    
        return () => {
            window.removeEventListener('resize', handleResize);
        };
    }, [handleResize, network]);
    

    useEffect(() => {
        handleResize();
    }, [handleResize]);

    const update = (newVal) => {
        setRepresentation(newVal);
        if (network) {
            network.setData(newVal);
        }
    };

    const clear = () => {
        update(emptyTree);
        setTree(new BinaryTree(null));
    };

    return (
        <React.Fragment>
            <Col>
                <InputForm update={update} tree={tree} />
                <Row>
                    <Col className="center danger">
                        <button className={"btn-clear"} onClick={() => { clear() }}>
                            <IconTrash />
                            Clear tree
                        </button>
                    </Col>
                </Row>
            </Col>
            <Col style={{padding: 30}}>
                <div style={{ height: '80vh'}}
                    ref={(divElement) => {
                        setDiv(divElement);
                    }}
                    className={"border border-dark"}>
                    <Graph
                        options={props.options}
                        updateTrigger={representation}
                        graph={{ edges: [], nodes: [] }}
                        getNetwork={network => {
                            setNetwork(network);
                        }} />
                </div>
            </Col>
        </React.Fragment>
    );
};

export default TreeGraph;
