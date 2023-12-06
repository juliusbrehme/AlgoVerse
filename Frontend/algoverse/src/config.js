// options 객체

export const options = {
    layout: {
        hierarchical: {
            direction: 'UD',
            nodeSpacing: 150,
            sortMethod: 'directed'
        }
    },
    edges: {
        color: "#000000",
        width: 2
    },
    interaction: {
        dragNodes: false,
        dragView: true,
        zoomView: true
    },
    height: `${window.innerHeight}px`,
    physics: {
        enabled: false
    },
    // 노드에 대한 스타일 지정
    nodes: {
        shape: "ellipse",
        color: {
            background: "#ffffff", // 기본 노드 배경색
            border: "#000000" // 기본 노드 테두리 색상
        },
        borderWidth: 2, // 기본 노드 테두리 두께
        font: "20px arial black"
    }
};
