const backendLocation = "http://localhost:8080";

let commentSection = document.getElementById('commentSection');

//used to display the button for deletion
let principalUsername = document.getElementById('principal-username').textContent;

//the csrf token is located set in the header as meta data -> in fragments/commons and from there we take it
const csrfHeaderName = document.head.querySelector('[name=_csrf_header]').content
const csrfHeaderValue = document.head.querySelector('[name=_csrf]').content

fetch(`${backendLocation}/api/comments`)
    .then((response) => response.json())
    .then((body) => {
            for (comment of body) {
                addCommentAsHtml(comment)
            }
        }
    )

function isAuthenticated(currentUser, commentAuthor) {
    return currentUser === commentAuthor;
}

function deleteComment(commentId) {
    fetch(`${backendLocation}/api/comments/${commentId}`, {
        method: 'DELETE',
        headers: {
            [csrfHeaderName]: csrfHeaderValue
        }
    }).then(res => console.log(res))
}

//with this method we generate html that we later put
function addCommentAsHtml(comment) {

    let commentHtml = '<div id="comment-content">\n'
    commentHtml += '<h2 id="comment-authorName">' + comment.authorName + '</h2>\n'
    commentHtml += '<p id="comment-text">' + comment.text + '</p>\n'
    commentHtml += '<span id="comment-date">' + comment.createTime + '</span>\n'

     if (isAuthenticated(principalUsername, comment.authorName)) {
        commentHtml += `<a class="btn btn-danger btn-sm text-black" href="${backendLocation}/comments" onclick="(deleteComment(${comment.id}))">Delete</a>\n`
     }

    commentHtml += '</div>\n'

    commentSection.innerHTML += commentHtml;
}


let commentForm = document.getElementById("commentForm");
commentForm.addEventListener("submit", (event) => {
    event.preventDefault();

    let text = document.getElementById("message-input").value

    let csrf = document.getElementById("csrf")

    let textComment = document.getElementById("message-input").value

    fetch(`${backendLocation}/api/comments`, {
        method: 'POST',
        headers: {
            //we set the settings of the http request here manually because we disabled
            //the automatic function of spring to do this with the preventDefault that we used on the submit button
            //now the submit button does not have default spring submit function
            //and we set the settings manually here

            //the first param here is the content-type that is expected
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            [csrfHeaderName]: csrfHeaderValue
            //here we set the CSRF
        },
        body: JSON.stringify({
            text: text
        })
    }).then((res) => {
        document.getElementById('message-input').value = ""
        let location = res.headers.get("Location")
        fetch(`${backendLocation}${location}`)
            .then(res => res.json())
            .then(body => addCommentAsHtml(body))
    })
})


