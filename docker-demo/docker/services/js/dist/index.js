$(document).ready(function () {

        $.get('http://localhost:8091/api/animals').done(function (animals) {
            animals.forEach(function (animal) {
                $('tbody').append(`
                    <tr>
                        <td>${animal.id}</td>
                        <td>${animal.name}</td>
                    </tr>
                `);
            });
        });

        $("#add").click(postHandler);


        function postHandler(e) {
            e.preventDefault();

            $.ajax({
                url: 'http://localhost:8091/api/animals',
                dataType: 'json',
                contentType: "application/json",
                type: 'POST',
                data: JSON.stringify({

                    name: $('#name').val(),

                })
            }).done(function (an) {
                $('#animals > tbody:last-child').after(`<tr><td>${an.id}</td><td>${an.name}</td></tr>`);
            }).fail(function (xhr, status, errorThrown) {
                console.log("Error: " + errorThrown);
                console.log("Status: " + status);
                console.dir(xhr);
            })

        }
    }
)

